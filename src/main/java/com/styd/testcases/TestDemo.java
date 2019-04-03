package com.styd.testcases;

import com.styd.config.TestConfig;
import com.styd.model.GetAgreementCase;
import com.styd.model.NearByShop;
import com.styd.utils.ConfigFile;
import com.styd.utils.DatabaseUtil;
import com.styd.utils.HttpClientUtil;
import com.styd.utils.UnicodeTransform;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.*;

public class TestDemo {
    private static Logger logger = Logger.getLogger(TestDemo.class);

    @Test(description = "获取品牌公告信息")
    public void get_agreement_test() throws IOException {

        SqlSession sqlSession = DatabaseUtil.getSqlSession(DatabaseUtil.DataSourceEnvironment.autotest);
        GetAgreementCase getAgreementCase = sqlSession.selectOne("get_agreement",1);

        //将接口参数存入Map中传递
        Map<String,String> map = new HashMap<String, String>();
        map.put("uid", String.valueOf(getAgreementCase.getUid()));

        //发送请求，获取结果
        String result = HttpClientUtil.doGet(ConfigFile.getUrl("get_agreement.uri"),map);
        JSONObject object = new JSONObject(result);

        //常用结果验证
        System.out.println(object.get("code"));
        Assert.assertEquals(object.get("code").toString(),"200");
        String content = object.getJSONObject("data").get("content").toString();
        logger.info(content);

        //查询应用数据库中的实际数据
        SqlSession sqlSession1 = DatabaseUtil.getSqlSession(DatabaseUtil.DataSourceEnvironment.rangym);
        String expectedContent = sqlSession1.selectOne(getAgreementCase.getExpected(),getAgreementCase.getUid());

        //将返回结果和查询的数据做比对
        Assert.assertEquals(expectedContent,content);
    }


    @Test(description = "获取门店信息")
    public void nearby_shop_test() throws IOException {
        SqlSession sqlSession = DatabaseUtil.getSqlSession(DatabaseUtil.DataSourceEnvironment.autotest);
        NearByShop nearby_shopCase = sqlSession.selectOne("nearby_shop",1);

        //将请求参数传入map中传递
        Map<String,String> map = new HashMap<String, String>();
        map.put("uid", String.valueOf(nearby_shopCase.getUid()));

        //发送请求，获取结果
        String result = HttpClientUtil.doGet(ConfigFile.getUrl("nearby_shop.uri"),map);
        JSONObject object = new JSONObject(result);

        //常用结果验证
 //       Assert.assertEquals(object.get("code").toString(),"200");
        logger.info(object.get("data").toString());

/*
        //查询应用数据库中的实际数据
        SqlSession sqlSession1 = DatabaseUtil.getSqlSession(DatabaseUtil.DataSourceEnvironment.rangym);
        List<Map<String,Object>> list = sqlSession1.selectList(nearby_shopCase.getExpected(), nearby_shopCase);
        for(Map<String,Object> map:list){
            System.out.println(map.get("shop_name"));
        }
*/
    }
}
