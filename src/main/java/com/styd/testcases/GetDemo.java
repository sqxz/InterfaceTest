package com.styd.testcases;

import com.styd.config.TestConfig;
import com.styd.utils.HttpClientUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;


public class GetDemo {
    private static Logger logger = Logger.getLogger(GetDemo.class.getName());

    @Test
    public void getRequest() throws IOException, URISyntaxException {
        String url = "http://localhost:8899/login";
        Map<String,String> map = new HashMap<String, String>();
        map.put("name","shen");
        map.put("password","111111");

        String result = HttpClientUtil.doGet(url,map);
        JSONObject object = new JSONObject(result);

        //获取cookie值，存入cookiestore
        String cookieName = "token";
        String cookieValue = object.get("token").toString();
        BasicClientCookie cookie = new BasicClientCookie(cookieName,cookieValue);
        cookie.setDomain("localhost");

        BasicCookieStore cookieStore = new BasicCookieStore();
        cookieStore.addCookie(cookie);
        HttpClientUtil.cookieStore = cookieStore;

        logger.info(object.toString());
    }

    @Test(dependsOnMethods = "getRequest")
    public void getRequest1(){
        String url = "http://localhost:8899/getwithparam";
        Map<String,String> map = new HashMap<String, String>();
        map.put("name","shen");
        map.put("password","111111");

        String result = HttpClientUtil.doGet(url,map);
        logger.info(result.toString());
    }
}
