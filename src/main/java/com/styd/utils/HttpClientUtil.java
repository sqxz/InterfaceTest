package com.styd.utils;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
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
    public static String doPost(String url, Map<String, String> param, Map<String,String> headers) {
    //    DefaultHttpClient httpClient = new DefaultHttpClient();
        String result = null;
        HttpResponse response = null;
        StringEntity entity = null;

        //解析请求参数
        JSONObject object = new JSONObject();
        for (String key : param.keySet()) {
            object.put(key, param.get(key));
        }

        try {
            entity = new StringEntity(object.toString());
            HttpPost post = new HttpPost(url);
            post.setEntity(entity);

            //解析头信息
            if (headers != null){
                for (String key:headers.keySet()){
                    post.addHeader(key,headers.get(key));
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
    public static String doPost(String url, Map<String, String> param) {
   //     DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        String result = null;

        List<NameValuePair> list = new LinkedList<NameValuePair>();

        for (String key:param.keySet()){
            list.add(new BasicNameValuePair(key,param.get(key)));
        }

        try {
            UrlEncodedFormEntity entityParam = new UrlEncodedFormEntity(list, "UTF-8");
            post.setEntity(entityParam);
        } catch (UnsupportedEncodingException e) {
            logger.error("不支持的编码格式");
            e.printStackTrace();
        }

        if (cookieStore != null){
            httpClient.setCookieStore(cookieStore);
        }

        HttpResponse response = null;
        try {
            response = httpClient.execute(post);
            result = EntityUtils.toString(response.getEntity(),"utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


}