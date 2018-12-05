package cn.itheima.mybatis.jdbc;

import cn.itheima.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

public class JdbcTest {
    private static SqlSessionFactory sqlSessionFactory;

    //静态代码块
    static {
        try {
            // 从classpath目录加载全局配置文件,以流的形式返回
            InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
            // 创建SqlSessionFactoryBuilder
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            // 获取sqlSessionFactory
            sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testJdbc() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //加载数据库驱动
            Class.forName("com.mysql.jdbc.Driver");
            //通过驱动管理类获取数据库链接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis?characterEncoding=utf-8", "root", "123");
            //定义sql语句,?表示占位符
            String sql = "select * from user where username like ?";
            //获得预处理statement
            preparedStatement = connection.prepareStatement(sql);
            //设置参数，第一个参数为sql语句中参数的序号（从1开始），第二个参数为设置的参数值
            preparedStatement.setString(1, "%张%");
            //向数据库发出sql执行查询，查询出结果集
            resultSet = preparedStatement.executeQuery();
            //遍历查询结果集
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + ":" + resultSet.getString("username"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放资源
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
    }

    @Test
    public void testMybatis() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = sqlSession.selectOne("cn.itheima.mybatis.pojo.User.selectById");
        System.out.println(user);
    }

    //查
    @Test
    public void testSelectById() {
        // 获取SqlSession,就有CURD的能力
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 参数1 : 映射文件中namespace + id,中间使用小点连接,写法类似包名+类名的写法
        // 参数2 : 执行sql语句时使用的参数
        User user = sqlSession.selectOne("cn.itheima.mybatis.pojo.User.selectById", 1);
        //释放资源,放在finally的代码块中
        sqlSession.close();
    }

    //模糊查
    @Test
    public void testSelectByName() {
        /**
         * 如果查找一个数据,可以使用selectOne,也可以使用selectList
         * 如果查找多个数据,只能使用selectList,如果使用了selectOne,会抛出TooManyResultsException
         * 如果返回值是list集合,定义映射文件的时候,resultType的值只用定义泛型的类型即可
         * 在进行SQL语句的拼接的时候,建议使用Concat函数,可以避免SQL注入
         * 如果使用${value}进行数据的拼接,格式是固定的,不能更改,只能是value
         */

        // 获取SqlSession,就有CURD的能力
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 参数1 : 映射文件中namespace + id,中间使用小点连接,写法类似包名+类名的写法
        // 参数2 : 执行sql语句时使用的参数
        //        List<User> list = sqlSession.selectList("cn.itheima.mybatis.pojo.User.selectByUserName, "张");
        List<User> userList = sqlSession.selectList("cn.itheima.mybatis.pojo.User.selectByUserName", "张");
        for (User user : userList) {
            System.out.println(user);
        }
        //释放资源,放在finally代码块中
        sqlSession.close();
    }

    // 商城系统,用户下单成功,会有一个页面,提示用户您的订单0101010已经下单成功了
    // SELECT LAST_INSERT_ID() 获取当前事务最后一个插入的数据的ID
    //增
    @Test
    public void testInsertUser() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = new User();
        user.setUsername("小青青");
        user.setSex("2");
        user.setBirthday(new Date());
        user.setAddress("东莞市");
        System.err.println("添加之前:" + user.getId());
        sqlSession.insert("cn.itheima.mybatis.pojo.User.insertUser2", user);
        System.err.println("添加之后 : " + user.getId());
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

    //删
    @Test
    public void deleteUserById() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //返回值表示受影响的行数
        int delete = sqlSession.delete("cn.itheima.mybatis.pojo.User.deleteById", 26);
        System.err.println(delete);
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

    //改
    @Test
    public void updateUserById() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setId(29);
        user.setUsername("大白");
        //返回值表示受影响的行数
        int update = sqlSession.update("cn.itheima.mybatis.pojo.User.updateById", user);
        System.err.println(update);
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

}

