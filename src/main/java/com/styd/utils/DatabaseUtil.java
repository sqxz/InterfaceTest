package com.styd.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;


public class DatabaseUtil {

    /**
     * 枚举databaseConfig.xml中的environment id
     */
    public static enum DataSourceEnvironment {
        autotest, rangym    // 枚举大小写必须与Mybatis.xml中environment id相同
    }

    /**
     * 获取sqlSession对象，驱动sql
     *
     */
    public static SqlSession getSqlSession(DataSourceEnvironment environment) throws IOException {
        Reader reader = Resources.getResourceAsReader("databaseConfig.xml");

        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader,environment.name());

        SqlSession sqlSession = factory.openSession();

        return sqlSession;
    }
}
