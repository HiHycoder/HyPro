package cn.itheima.mapper;

import cn.itheima.mybatis.mapper.OrderMapper;
import cn.itheima.mybatis.pojo.OrderUser;
import cn.itheima.mybatis.pojo.Orders;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 代理方式的测试类   订单模块
 */
public class OrderMapperTest {

    private static SqlSessionFactory sqlSessionFactory;

    //静态代码块
    static {
        try {
            InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //pojo中的属性名称和表的列名不一致
    @Test
    public void testFindOrderList() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //通过字节码获得代理对象
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        List<Orders> orderList = orderMapper.findOrderList();
        for (Orders orders : orderList) {
            System.out.println(orders);
        }
        sqlSession.close();
    }

    //使用resultMap查询所有订单
    @Test
    public void testFindOrderListResultMap() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //通过字节码获得代理对象
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        List<Orders> orderListResultMap = orderMapper.findOrderListResultMap();
        for (Orders orders : orderListResultMap) {
            System.out.println(orders);
        }
        sqlSession.close();
    }

    //一对一 关联映射   方法一 : 使用resultType实现
    //需求 : 查询订单信息关联用户
    @Test
    public void testFindOrderUserList() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取到mapper代理对象
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        List<OrderUser> orderUserList = orderMapper.findOrderUserList();
        for (OrderUser orders : orderUserList) {
            System.out.println(orders);
        }
        sqlSession.close();
    }

    //一对一 关联映射    方法二 : 使用resultMap
    @Test
    public void testFindOrderUserResultMap() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获得mapper对象
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        List<Orders> ordersList = mapper.findOrderUserResultMap();
        for (Orders orders : ordersList) {
            System.out.println(orders);
        }
        sqlSession.close();
    }


}
