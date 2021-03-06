package cn.itheima.mybatis.mapper;

import cn.itheima.mybatis.pojo.OrderUser;
import cn.itheima.mybatis.pojo.Orders;

import java.util.List;

/**
 * 订单的接口
 */
public interface OrderMapper {

     List<Orders> findOrderList();

     List<Orders> findOrderListResultMap();

     //形式一: 使用resultType 关联映射一对一
     List<OrderUser> findOrderUserList();

     //形式二: 使用resultMap 关联映射一对一
     List<Orders> findOrderUserResultMap();

}
