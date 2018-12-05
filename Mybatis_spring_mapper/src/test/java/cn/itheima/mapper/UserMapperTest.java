package cn.itheima.mapper;

import cn.itheima.mybatis.mapper.UserMapper;
import cn.itheima.mybatis.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * mapper代理的测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testFindById() {
        User user = userMapper.findById(1);
        System.out.println(user);
    }

    @Test
    public void testFindByName() {
        List<User> userList = userMapper.findByName("张");
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void testInsertUser() {
        User user = new User();
        user.setUsername("小星星");
        user.setAddress("天津市");
        userMapper.insertUser(user);
    }

}
