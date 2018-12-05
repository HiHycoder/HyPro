package cn.itheima.mybatis.mapper;

import cn.itheima.mybatis.pojo.User;

import java.util.List;

public interface UserMapper {

    /**
     * Mapper代理对象接口
     *
     * 1、	Mapper接口方法名和Mapper.xml中定义的statement的id相同
     * 2、	Mapper接口方法的输入参数类型和mapper.xml中定义的statement的parameterType的类型相同
     * 3、	Mapper接口方法的输出参数类型和mapper.xml中定义的statement的resultType的类型相同
     */

    User findById(Integer id);

    List<User> findByName(String username);

    void insertUser(User user);

}
