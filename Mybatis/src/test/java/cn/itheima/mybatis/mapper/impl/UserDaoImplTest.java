package cn.itheima.mybatis.mapper.impl;
import cn.itheima.mybatis.dao.UserDao;
import cn.itheima.mybatis.dao.impl.UserDaoImpl;
import cn.itheima.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class UserDaoImplTest {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            //第一步 : 创建 SqlSessionFactoryBuilder对象
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            //第二步：从classpath目录下加载配置文件,以流的形式返回。
            InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
            //第三步：获取SqlSessionFactory对象
            sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //每一个对象都应该去使用自己的Sqlsession 单例和生命周期有关
     //查
    @Test
    public void testfindById() throws IOException {
        UserDao userDao = new UserDaoImpl(sqlSessionFactory);
        User user = userDao.selectById(27);
        System.out.println(user);
    }

    //模糊查
    @Test
    public void testSelectByUserName() {
        /**   注意 :
         * 如果查找的是一个数据,可以使用selectOne,也可以使用selectList
         * 但是如果查找多个数据,只能使用selectList,否则会报错TooManyResultException
         * 如果返回值是list集合,定义映射文件的时候,resultType的值只用定义泛型的类型即可
         * 在进行SQL语句拼接的时候,建议使用Concat函数,#{}可以有效避免sql注入问题
         * 如果使用${value}进行数据的拼接,格式是固定的,{}里面的值只能是value!
         */
        UserDao userDao = new UserDaoImpl(sqlSessionFactory);
        List<User> userList = userDao.selectByUserName("张");
        for (User user : userList) {
            System.out.println(user);
        }

    }

    //增
    @Test
    public void testInsertUser() {
        UserDao userDao = new UserDaoImpl(sqlSessionFactory);
        User user = new User();
        user.setUsername("黄晓明");
        user.setSex("1");
        user.setBirthday(new Date());
        user.setAddress("天津");
        userDao.insertUser(user);
        System.out.println(user);
    }

}
