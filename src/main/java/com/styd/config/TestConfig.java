package com.styd.config;

import org.apache.http.impl.client.DefaultHttpClient;

/**
 * 初始化defaultHttpClient对象
 * 存放token信息
 */
public class TestConfig {
    public static DefaultHttpClient defaultHttpClient = new DefaultHttpClient() ;

    public static String token;

}
