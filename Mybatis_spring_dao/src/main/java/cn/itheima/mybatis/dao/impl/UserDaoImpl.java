package cn.itheima.mybatis.dao.impl;

import cn.itheima.mybatis.dao.UserDao;
import cn.itheima.mybatis.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 实现类
 */
@Repository
public class UserDaoImpl extends BaseDao implements UserDao {

    //父类中已经提供了set()方法,实现SqlSessionFactory
    public User findById(Integer id) {
        SqlSession sqlSession = getSqlSession();
        //名称空间 + statement的id
        User user = sqlSession.selectOne("aa.findById", id);
        return user;
    }

    public List<User> findByName(String username) {
        SqlSession sqlSession = getSqlSession();
        List<User> userList = sqlSession.selectList("aa.findByName2", username);
        for (User user : userList) {
            System.out.println(user);
        }
        return userList;
    }

    public void InsertUser(User user) {
        SqlSession sqlSession = getSqlSession();
        sqlSession.insert("aa.insertUser", user);
    }
}
