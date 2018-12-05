package cn.itheima.mybatis;

import cn.itheima.mybatis.dao.UserDao;
import cn.itheima.mybatis.dao.impl.UserDaoImpl;
import cn.itheima.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 测试 dao 接口
 */
public class AppTest {

      private static SqlSessionFactory sqlSessionFactory = null;

      @Before
      public void init() throws IOException {
          //第一步 : 创建SqlSessionFactoryBuilder
          SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
          //第二步 : 加载配置文件
          InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
          //第三步 :  获取SqlSessionFactory
          sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
      }


      //测试模糊查询
      @Test
    public void testSelectByUserName(){

          UserDao userDao = new UserDaoImpl(sqlSessionFactory);
          List<User> userList = userDao.selectByUserName("张");
          for (User user : userList) {
              System.out.println(user);
          }
      }

      //插入用户数据
    @Test
    public  void testInsertUser(){
        UserDao userDao = new UserDaoImpl(sqlSessionFactory);
        User user = new User();
        user.setUsername("小白");
        userDao.insertUser(user);
    }
}
