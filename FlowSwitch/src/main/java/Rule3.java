import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
//版本1下无异常
@Rule
public class Rule3 {
    @Condition
    public boolean isdo(@Fact("value") Integer value,@Fact("flag") Integer flag)
    {
        return value>0.8&&flag==0;   }

    @Action
    public void useyaml3() {
        System.out.println("版本1无异常");
    }


}
