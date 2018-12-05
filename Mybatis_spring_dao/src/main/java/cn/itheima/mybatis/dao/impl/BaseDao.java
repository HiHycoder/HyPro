package cn.itheima.mybatis.dao.impl;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * dao的基类   去注入sqlSessionFactory,提供set()方法
 */
public class BaseDao extends SqlSessionDaoSupport {

    //父类中提供SqlSessionFactory的set()方法给子类用,模拟Spring整合
    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

}
