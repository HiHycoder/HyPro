package cn.itheima.mapper;

import cn.itheima.mybatis.mapper.UserMapper;
import cn.itheima.mybatis.pojo.QueryVo;
import cn.itheima.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 代理方式的测试类    用户模块
 */
public class UserMapperTest {

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

    @Test
    public void findById() {
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
    public void findByName() {
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
    public void insertUser() {
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

    //包装类的参数类型
    @Test
    public void findByQueryVo() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //通过字节码文件获得代理对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //创建QueryVo对象
        QueryVo queryVo = new QueryVo();
        //创建User对象
        User user = new User();
        user.setId(10);
        user.setUsername("张");
        queryVo.setUser(user);
        //执行查询
        User result = mapper.findByQueryVo(queryVo);
        System.out.println(result);
        sqlSession.close();
    }

    //简单类的结果类型   返回一条记录,只取第一列!
    @Test
    public void findUserCount() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //通过字节码文件获得代理对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Integer count = mapper.findUserCount();
        System.out.println(count);
        sqlSession.close();
    }

    //sql动态 pojo传递参数
    @Test
    public void testFindByIf() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //通过字节码文件获得代理对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //设置查询对象User
        User user = new User();
        user.setUsername("张");
        //执行查询
        List<User> userList = mapper.findByIf(user);
        for (User user2 : userList) {
            System.out.println(user2);
        }
        sqlSession.close();
    }

    //sql动态,传递多个用户的id
    @Test
    public void testFindByForeach() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获得mapper代理对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //设置查询条件
        QueryVo queryVo = new QueryVo();
        List<Integer> ids = new ArrayList<>();
        // and id in(1,10,16,27,31)
        ids.add(1);
        ids.add(10);
        ids.add(16);
        ids.add(27);
        queryVo.setIds(ids);
        //执行查询
        List<User> userList = mapper.findByForeach(queryVo);
        for (User user : userList) {
            System.out.println(user);
        }
        sqlSession.close();
    }

    @Test
    public void testUpdateUserBySet() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获得mapper对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setId(31);
        user.setUsername("高小青");
        user.setSex("2");
        user.setBirthday(new Date());
        user.setAddress("惠州市");
        mapper.updateUserBySet(user);
        sqlSession.commit();
        sqlSession.close();
    }

    //配置关联一对多  查询客户信息的同时关联订单   没有下过订单的客户信息也能展示出来!
    @Test
    public void testFindUserWithOrders() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> results = mapper.findUserWithOrders();
        for (User user : results) { //
            System.err.println(user);
        }
        sqlSession.close();
    }

}
