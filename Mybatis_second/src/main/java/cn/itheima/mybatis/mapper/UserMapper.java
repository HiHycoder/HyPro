package cn.itheima.mybatis.mapper;

import cn.itheima.mybatis.pojo.QueryVo;
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

    //包装类的参数类型
    User findByQueryVo(QueryVo queryVo);

    //简单类的结果类型
    Integer findUserCount();

    //pojo传递
    List<User> findByIf(User user);

    //动态sql,传递多个id foreach标签
    List<User> findByForeach(QueryVo queryVo);

    //set(标签)
    void updateUserBySet(User user);

    //配置关联一对多  查询客户信息的同时关联订单
    List<User> findUserWithOrders();
}
