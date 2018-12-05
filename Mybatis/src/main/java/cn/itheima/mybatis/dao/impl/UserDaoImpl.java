package cn.itheima.mybatis.dao.impl;

import cn.itheima.mybatis.dao.UserDao;
import cn.itheima.mybatis.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * 实现类
 */
public class UserDaoImpl implements UserDao {

    // 1. 获取sqlsessionfactroy
    private SqlSessionFactory sqlSessionFactory;

    //模拟整合Spring
    public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }


    @Override
    public User selectById(Integer id) {
        // 2. 获取sqlsession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取user对象 CURD
        User user = sqlSession.selectOne("cn.itheima.mybatis.pojo.User.selectById", id);
        //释放资源
        sqlSession.close();
        return user;
    }

    @Override
    public List<User> selectByUserName(String username) {
        // 2. 获取sqlsession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.CURD对象
        List<User> userList = sqlSession.selectList("cn.itheima.mybatis.pojo.User.selectByUserName", username);
        //释放资源
        sqlSession.close();
        return userList;
    }

    @Override
    public void insertUser(User user) {
        // 2. 获取sqlsession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.CURD对象
        sqlSession.insert("cn.itheima.mybatis.pojo.User.insertUser", user);
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }
}
