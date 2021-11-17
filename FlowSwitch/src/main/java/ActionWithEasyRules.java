import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.api.RulesEngineParameters;
import org.jeasy.rules.core.DefaultRulesEngine;


public class ActionWithEasyRules extends Thread{
    //采用flag判断当前使用的reviews是什么版本,flag=0使用的是v1版本，flag=1使用的是v3版本，默认初始为v1版本
    public static int flag=0;
    public void run(){
        while(true){
            try {

                // 创建规则引擎
                RulesEngineParameters parameters = new RulesEngineParameters().skipOnFirstAppliedRule(true);
                RulesEngine rulesengine = new DefaultRulesEngine(parameters);

                // 创建规则
                Rules rules = new Rules();
                rules.register(new Rule1());
                rules.register(new Rule2());
                rules.register(new Rule3());

                // 执行规则
                Facts facts = new Facts();
                Get get=new Get();
                //paramValue1为查询版本1成功率的语句，paramValue2为查询版本3成功率的语句
                String paramValue1="sum(rate(istio_requests_total{reporter=\"destination\",destination_workload_namespace=~\"default\",destination_workload=~\"reviews-v1\",response_code!~\"5.*\"}[5m])) / sum(rate(istio_requests_total{reporter=\"destination\",destination_workload_namespace=~\"default\",destination_workload=~\"reviews-v1\"}[5m]))";
                String paramValue2="sum(rate(istio_requests_total{reporter=\"destination\",destination_workload_namespace=~\"default\",destination_workload=~\"reviews-v3\",response_code!~\"5.*\"}[5m])) / sum(rate(istio_requests_total{reporter=\"destination\",destination_workload_namespace=~\"default\",destination_workload=~\"reviews-v3\"}[5m]))";

                if(flag==0) {
                    String getvalue=get.getvalue(paramValue1);
                    Integer value=Integer.parseInt(getvalue);
                    System.out.println(value);
                    facts.put("value", value);
                    facts.put("flag",flag);
                    rulesengine.fire(rules, facts);
                }
                else if(flag==1){
                    String getvalue=get.getvalue(paramValue2);
                    Integer value=Integer.parseInt(getvalue);
                    System.out.println(value);
                    facts.put("value", value);
                    facts.put("flag",flag);
                    rulesengine.fire(rules, facts);
                }
                else{
                    System.out.println("flag的值有错误!");
                }


                System.out.println("一分钟运行一次");
                sleep(60*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }



    public static void main(String[] args) {

        ActionWithEasyRules testThread = new ActionWithEasyRules();
        testThread.start();


    }
}
