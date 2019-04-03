package com.styd.testcases;

import com.styd.utils.HttpClientUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class PostDemo {

    private static Logger logger = Logger.getLogger(PostDemo.class.getName());


    @Test(dependsOnMethods = "com.styd.testcases.GetDemo.getRequest")
    public void  postDemo() throws IOException {

        Map<String,String> param = new HashMap<String, String>();
        param.put("name","shen");
        param.put("password","111111");


        Map<String,String> headers = new HashMap<String, String>();
        headers.put("content-type","application/json");

        String object =
        HttpClientUtil.doPost("http://localhost:8899/getDeptUser",param,headers);

        logger.info(object);
    }

    @Test
    public void postDemo1(){
        String url = "http://localhost:8899/postWithFormParam";
        Map<String,String> param = new HashMap<String, String>();
        param.put("name","shen");
        param.put("password","111111");
        String object =
                HttpClientUtil.doPost(url,param);
        logger.info(object);

    }
}
