import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
//版本1异常流量切换到版本3
@Rule
public class Rule1 {
    @Condition
    public boolean isdo(@Fact("value") Integer value,@Fact("flag") Integer flag)
    {
        return value<0.8&&flag==0;   }

    @Action
    public void useyaml1() {
        Cmd cmd=new Cmd();
        String a="kubectl apply -f D:\\istiol\\istio-1.6.7\\samples\\bookinfo\\networking\\virtual-service-reviews-v3.yaml";

        cmd.docmd(a);
    }


}
