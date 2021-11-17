
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

//执行CMD命令配置yaml文件
public class Cmd {
    public void docmd(String a) {
        Process p;
        try {
            //exec执行cmd命令
            p = Runtime.getRuntime().exec(a);
            //获取CMD命令结果的输出流
            InputStream fis = p.getInputStream();
            InputStreamReader isr = new InputStreamReader(fis, "Shift_JIS");
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null) {
                //输出读取的内容
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

