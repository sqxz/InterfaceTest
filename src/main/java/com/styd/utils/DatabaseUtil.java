package com.styd.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;


public class DatabaseUtil {

 /*   *//**
     * 枚举databaseConfig.xml中的environment id
     *//*
    public enum DataSourceEnvironment {
       AUTOTEST ,RANGYM   // 枚举大小写必须与Mybatis.xml中environment id相同
    }*/

    /**
     * 获取sqlSession对象，驱动sql
     *
     */
    public static SqlSession getSqlSession(DataBaseEnvironment environment) {
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader("databaseConfig.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }

        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader,environment.toString());

        SqlSession sqlSession = factory.openSession();

        return sqlSession;
    }

    public static Object getTestDataObject(String selectId,int sqlId){
        SqlSession sqlSession = DatabaseUtil.getSqlSession(DataBaseEnvironment.AUTOTEST);
        return sqlSession.selectOne(selectId,sqlId);
    }
}
