package cn.itheima.mybatis.dao;

import cn.itheima.mybatis.pojo.User;

import java.util.List;

/**
 * 接口
 */
public interface UserDao {

    public User selectById(Integer id);

    public List<User> selectByUserName(String username);

    public void insertUser(User user);

}
