import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
//版本3异常流量切换到版本1
@Rule
public class Rule2 {
    @Condition
    public boolean isdo(@Fact("value") Integer value,@Fact("flag") Integer flag)
    {
        return value<0.8&&flag==1;   }

    @Action
    public void useyaml2() {
        Cmd cmd=new Cmd();
        String a="kubectl apply -f D:\\istiol\\istio-1.6.7\\samples\\bookinfo\\networking\\virtual-service-all-v1.yaml";
        cmd.docmd(a);

    }


}
