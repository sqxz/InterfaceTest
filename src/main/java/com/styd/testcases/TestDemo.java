package com.styd.testcases;

import com.styd.config.TestConfig;
import com.styd.model.GetAgreementCase;
import com.styd.model.NearByShop;
import com.styd.utils.ConfigFile;
import com.styd.utils.DatabaseUtil;
import com.styd.utils.UnicodeTransform;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.*;

public class TestDemo {

    @Test(description = "获取品牌公告信息")
    public void get_agreement_test() throws IOException {
        SqlSession sqlSession = DatabaseUtil.getSqlSession(DatabaseUtil.DataSourceEnvironment.autotest);
        GetAgreementCase getAgreementCase = sqlSession.selectOne("get_agreement",1);
        //发送请求，获取结果
        String result = getResult(getAgreementCase);

        //常用结果验证
        JSONObject object = new JSONObject(result);
        System.out.println(object.get("code"));
        Assert.assertEquals(object.get("code").toString(),"200");
        String content = object.getJSONObject("data").get("content").toString();

        /*System.out.println(getAgreementCase.getExpected()+"  "+getAgreementCase.getUid());*/

        //查询应用数据库中的实际数据
        SqlSession sqlSession1 = DatabaseUtil.getSqlSession(DatabaseUtil.DataSourceEnvironment.rangym);
        String expectedConetent = sqlSession1.selectOne(getAgreementCase.getExpected(),getAgreementCase.getUid());

        //将返回结果和查询的数据做比对
        Assert.assertEquals(expectedConetent,content);
    }


    @Test(description = "获取门店信息")
    public void nearby_shop_test() throws IOException {
        SqlSession sqlSession = DatabaseUtil.getSqlSession(DatabaseUtil.DataSourceEnvironment.autotest);
        NearByShop nearby_shopCase = sqlSession.selectOne("nearby_shop",1);

        //发送请求，获取结果
        String result = getResult(nearby_shopCase);

        //常用结果验证
        JSONObject object = new JSONObject(result);
        Assert.assertEquals(object.get("code").toString(),"200");

        //查询应用数据库中的实际数据
        SqlSession sqlSession1 = DatabaseUtil.getSqlSession(DatabaseUtil.DataSourceEnvironment.rangym);
        System.out.println(nearby_shopCase.getExpected() + nearby_shopCase.getUid());

        Map<String,Object> map = sqlSession1.selectOne(nearby_shopCase.getExpected(), nearby_shopCase.getUid());

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }

        System.out.println("---------------------------------");

        JSONObject data = object.getJSONObject("data");

        Iterator<String> it=
                object.getJSONObject("data").keys();

        while (it.hasNext()){
            String key = it.next();
            System.out.println(key+": "+data.get(key).toString());
        }

        Assert.assertEquals(String.valueOf(map.get("id")),data.get("shop_id"));
        Assert.assertEquals(String.valueOf(map.get("address")),data.get("address"));
        Assert.assertEquals(String.valueOf(map.get("shop_name")),data.get("shop_name"));
    }





    private  String getResult(NearByShop nearByShop) throws IOException {
        //配置请求参数，从数据库中获取
        List<NameValuePair> list = new LinkedList<NameValuePair>();
        BasicNameValuePair param1 = new BasicNameValuePair("uid",String.valueOf(nearByShop.getUid()));
        list.add(param1);
        String params = EntityUtils.toString(new UrlEncodedFormEntity(list, Consts.UTF_8));
        System.out.println(params);
        //拼接get请求url
        HttpGet get = new HttpGet(ConfigFile.getUrl("nearby_shop.uri") + "?" +params);
        get.addHeader("uid", String.valueOf(nearByShop.getUid()));
        //执行get请求，返回response
        HttpResponse response =  TestConfig.defaultHttpClient.execute(get);
        String result =  EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(UnicodeTransform.decodeUnicode(result));
        return result;
    }

    //获取请求结果
    private String getResult(GetAgreementCase getAgreementCase) throws IOException {
        List<NameValuePair> list = new LinkedList<NameValuePair>();
        BasicNameValuePair param1 = new BasicNameValuePair("uid",String.valueOf(getAgreementCase.getUid()));
        list.add(param1);
        String params = EntityUtils.toString(new UrlEncodedFormEntity(list, Consts.UTF_8));
        System.out.println(params);
        HttpGet get = new HttpGet(ConfigFile.getUrl("get_agreement.uri") + "?" +params);
        HttpResponse response =  TestConfig.defaultHttpClient.execute(get);
        String result =  EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(UnicodeTransform.decodeUnicode(result));
        return result;
    }
}
