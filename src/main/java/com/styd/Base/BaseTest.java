package com.styd.Base;

import com.styd.model.LoginCase;
import com.styd.utils.DatabaseUtil;
import com.styd.utils.HttpClientUtil;
import com.styd.utils.ReadPropertiesFile;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;

/**
 * @ClassName BaseTest
 * @Description TDD
 * @Author shenzhenghuan
 * @Date 2019/4/28 20:13
 **/
public class BaseTest {
    private Logger logger = Logger.getLogger(BaseTest.class);

    @BeforeSuite
    public void loginSuccess() {
        //数据库中查询测试数据的对象
        LoginCase loginCase = (LoginCase) DatabaseUtil.getTestDataObject("login", 1);
        //组装请求参数
        JSONObject params = new JSONObject();
        params.put("name", loginCase.getName());
        params.put("password", loginCase.getPassword());
        //发送请求，获得请求结果
        String result = HttpClientUtil.doPost(ReadPropertiesFile.getUrl("account_login.uri"), params);
        //转化为json，解析返回结果
        JSONObject response = new JSONObject(result);
        //获取token，后续接口的header中需要添加
        HttpClientUtil.token = response.getJSONObject("data").get("token").toString();
        logger.info(response.toString());
        //断言
        Assert.assertEquals(response.get("msg"), loginCase.getExpected());
    }
}