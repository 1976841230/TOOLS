package com.kuranado.ocr;

import com.baidu.aip.ocr.AipOcr;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Created by JING on 2018/2/26.
 */
@Component
public class Ocr {

    //设置APPID/AK/SK
    @Value("${baidu.aip.APP_ID}")
    private String APP_ID;
    @Value("${baidu.aip.API_KEY}")
    private  String API_KEY;
    @Value("${baidu.aip.SECRET_KEY}")
    private  String SECRET_KEY;

    public String ocr(String path) {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        //client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
        //client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        //System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        // 调用接口
        JSONObject res = client.basicGeneral(path, new HashMap<String, String>());
        return res.toString(2);
    }
}
