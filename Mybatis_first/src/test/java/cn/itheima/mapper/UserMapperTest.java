package cn.itheima.mapper;
import cn.itheima.mybatis.mapper.UserMapper;
import cn.itheima.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * 代理方式的测试类
 */
public class UserMapperTest {

     private static SqlSessionFactory sqlSessionFactory ;


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


     @Test
    public void findById(){
         SqlSession sqlSession = sqlSessionFactory.openSession();
         //通过字节码文件得到代理对象
         UserMapper mapper = sqlSession.getMapper(UserMapper.class);
         System.err.println(mapper);
         User user = mapper.findById(1);
         System.out.println(user);
         //释放资源
         sqlSession.close();
     }


     @Test
    public void findByName(){
         SqlSession sqlSession = sqlSessionFactory.openSession();
         //通过字节码文件得到代理对象
         UserMapper mapper = sqlSession.getMapper(UserMapper.class);
         List<User> userList = mapper.findByName("张");
         for (User user : userList) {
             System.out.println(user);
         }
         sqlSession.close();
     }

     @Test
    public void insertUser(){
         SqlSession sqlSession = sqlSessionFactory.openSession();
         //通过字节码文件得到代理对象
         UserMapper mapper = sqlSession.getMapper(UserMapper.class);
         //创建user对象
         User user = new User();
         user.setUsername("召唤师");
         user.setSex("2");
         user.setBirthday(new Date());
         user.setAddress("田径");
         mapper.insertUser(user);
         sqlSession.commit();
         sqlSession.close();
     }

}
