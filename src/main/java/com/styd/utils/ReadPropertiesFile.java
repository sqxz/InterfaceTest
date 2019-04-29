package com.styd.utils;


import java.util.Locale;
import java.util.ResourceBundle;

public class ReadPropertiesFile {
    private static ResourceBundle bundle = ResourceBundle.getBundle("testApi", Locale.CHINA);

    /**
     * 获取接口测试URL
     * @param uriName
     * @return
     */
    public static  String getUrl(String uriName){

        String address = bundle.getString("test.url");
        String uri = bundle.getString(uriName);
        String testUrl;

        testUrl = address + uri;
        return testUrl;
    }
}
