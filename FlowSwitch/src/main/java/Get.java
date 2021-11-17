
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.net.URISyntaxException;

public class Get {
    String getvalue(String str) {
        //HTTP客户端连接工具
        CloseableHttpClient httpClient= HttpClients.createDefault();
        //参数里有特殊字符，不能直接写成String（会报Illegal Character错误），用URIBuilder构造。
        URIBuilder uri=null;
        HttpGet get =null;
        String value1=null;
        try {
            uri=new URIBuilder("http://localhost:60898/api/v1/query");
            uri.addParameter("query",str);
            //uri此时为prometheus的查询地址
            get=new HttpGet(uri.build());
        } catch (
                URISyntaxException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject=null;
        CloseableHttpResponse response=null;

        try {
            // 执行请求并接收并转换得到jsonObject
            response = httpClient.execute(get);
            String resStr = EntityUtils.toString(response.getEntity(), "UTF-8");
            jsonObject = JSONObject.parseObject(resStr);
            JSONArray result=jsonObject.getJSONObject("data").getJSONArray("result");
            JSONArray value=null;
           //根据返回的JSON结构获取成功率的值
            value=result.getJSONObject(0).getJSONArray("value");
            value1=value.getString(1);



        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return value1;

    }





}
