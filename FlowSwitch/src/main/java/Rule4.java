import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
//版本3下无异常
@Rule
public class Rule4 {
    @Condition
    public boolean isdo(@Fact("value") Integer value,@Fact("flag") Integer flag)
    {
        return value>0.8&&flag==1;   }

    @Action
    public void useyaml4() {
        System.out.println("版本3无异常");
    }


}
