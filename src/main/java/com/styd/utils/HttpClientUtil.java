package com.styd.utils;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class HttpClientUtil {

    private static Logger logger = Logger.getLogger(HttpClientUtil.class);
    public static CookieStore cookieStore = null;
    public static DefaultHttpClient httpClient = new DefaultHttpClient();
    public static String contentType = "application/json;charset=UTF-8";
    public static String appId = "11111";
    public static String appVersion = "1";
    public static String token = "";



    /**
     * @Author shenzhenghuan
     * @Description //TODO 
     * @Date 19:21 2019/4/3
     * @Param [url, param]
     * @return org.json.JSONObject
     **/
    public static String doGet(String url, Map<String, String> param) {
  //      DefaultHttpClient httpClient = new DefaultHttpClient();
        URIBuilder builder = null;
        String result = null;
        try {
            builder = new URIBuilder(url);
            for (String key : param.keySet()) {
                builder.addParameter(key, param.get(key));
            }
            HttpGet get = new HttpGet(builder.build());

            //request 必传参数
            get.addHeader("app-id",appId);
            get.addHeader("app-version",appVersion);
            get.addHeader("token",token);


            HttpResponse response = httpClient.execute(get);
            result = EntityUtils.toString(response.getEntity());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @Author shenzhenghuan
     * @Description //TODO
     * @Date 19:19 2019/4/3
     * @Param [url, param, headers]
     * @return org.json.JSONObject
     **/
    public static String doPost(String url, JSONObject object, Map<String,String> headers) {
    //    DefaultHttpClient httpClient = new DefaultHttpClient();
        String result = null;
        HttpResponse response = null;
        StringEntity entity = null;

        try {
            entity = new StringEntity(object.toString());
            entity.setContentType("text/json");
            HttpPost post = new HttpPost(url);

            post.setEntity(entity);

            //固定头信息
            post.setHeader("content-type",contentType);
            post.setHeader("app-id",appId);
            post.setHeader("app-version",appVersion);
            post.setHeader("token",token);


            //解析其余头信息
            if (headers != null){
                for (String key:headers.keySet()){
                    post.setHeader(key,headers.get(key));
                }
            }

            //设置请求cookiestore
            if (cookieStore != null){
                httpClient.setCookieStore(cookieStore);
            }

            response = httpClient.execute(post);
            result = EntityUtils.toString(response.getEntity(),"utf-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }


    /**
     * @Author shenzhenghuan
     * @Description //TODO
     * @Date 19:20 2019/4/3
     * @Param [url, param]
     * @return org.json.JSONObject
     **/
    public static String doPost(String url, JSONObject object) {
        String result = null;
        HttpResponse response = null;
        StringEntity entity = null;

        try {
            entity = new StringEntity(object.toString(),"utf-8");
            entity.setContentType("text/json");
            HttpPost post = new HttpPost(url);

            post.setEntity(entity);

            //固定头信息
            post.setHeader("content-type",contentType);
            post.setHeader("app-id",appId);
            post.setHeader("app-version",appVersion);
            post.setHeader("token",token);

            //设置请求cookiestore
            if (cookieStore != null){
                httpClient.setCookieStore(cookieStore);
            }

            response = httpClient.execute(post);
            result = EntityUtils.toString(response.getEntity(),"utf-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * @Author shenzhenghuan
     * @Description //TODO 
     * @Date 22:23 2019/4/11
     * @Param []
     * @return java.lang.String
     **/
    public static String doPut(String url, JSONObject object){
        String result = null;
        HttpResponse response = null;
        StringEntity entity = null;

        try {
            entity = new StringEntity(object.toString(),"utf-8");
            entity.setContentType("text/json");

            HttpPut put = new HttpPut(url);


            put.setEntity(entity);

            //固定头信息
            put.setHeader("content-type",contentType);
            put.setHeader("app-id",appId);
            put.setHeader("app-version",appVersion);
            put.setHeader("token",token);

            //设置请求cookiestore
            if (cookieStore != null){
                httpClient.setCookieStore(cookieStore);
            }

            response = httpClient.execute(put);
            result = EntityUtils.toString(response.getEntity(),"utf-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }


}