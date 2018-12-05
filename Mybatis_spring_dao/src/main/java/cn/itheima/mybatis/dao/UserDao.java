package cn.itheima.mybatis.dao;

import cn.itheima.mybatis.pojo.User;

import java.util.List;

/**
 * dao接口
 */
public interface UserDao {

    User findById(Integer id);

    List<User> findByName(String username);

    void InsertUser(User user);

}
