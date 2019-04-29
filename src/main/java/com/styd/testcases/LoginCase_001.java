package com.styd.testcases;

import com.mongodb.util.JSON;
import com.styd.model.LoginCase;
import com.styd.utils.DataBaseEnvironment;
import com.styd.utils.DatabaseUtil;
import com.styd.utils.HttpClientUtil;
import com.styd.utils.ReadPropertiesFile;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LoginCase
 * @Description 登录接口，获取token
 * @Author shenzhenghuan
 * @Date 2019/4/19 19:27
 **/
public class LoginCase_001 {

    private static Logger logger = Logger.getLogger(LoginCase_001.class);

    @Test(description = "登录成功")
    public static void  loginSuccess() {
        //数据库中查询测试数据的对象
        LoginCase loginCase = (LoginCase) DatabaseUtil.getTestDataObject("login",1);
        //组装请求参数
        JSONObject params = new JSONObject();
        params.put("name",loginCase.getName());
        params.put("password",loginCase.getPassword());
        //发送请求，获得请求结果
        String result = HttpClientUtil.doPost(ReadPropertiesFile.getUrl("account_login.uri"),params);
        //转化为json，解析返回结果
        JSONObject response = new JSONObject(result);
        //获取token，后续接口的header中需要添加
        HttpClientUtil.token = response.getJSONObject("data").get("token").toString();
        logger.info(response.toString());
        //断言
        Assert.assertEquals(response.get("msg"),loginCase.getExpected());
    }

    @Test(description = "登录失败，登录密码错误")
    public void  loginFail() {
        LoginCase loginCase = (LoginCase) DatabaseUtil.getTestDataObject("login",2);
        JSONObject params = new JSONObject();
        params.put("name",loginCase.getName());
        params.put("password",loginCase.getPassword());
        String result = HttpClientUtil.doPost(ReadPropertiesFile.getUrl("account_login.uri"),params);
        JSONObject object = new JSONObject(result);
        logger.info(object.toString());
        Assert.assertEquals(object.get("msg"),loginCase.getExpected());
    }


}
