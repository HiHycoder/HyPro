package cn.itheima.dao;

import cn.itheima.mybatis.dao.UserDao;
import cn.itheima.mybatis.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * 测试传统dao接口
 *
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext_dao.xml")
public class UserDaoImplTest {

@Autowired
private  UserDao userDao;

    @Test
    public void testFindById() {
        User user = userDao.findById(10);
        System.out.println(user);
    }

    @Test
    public void testFindByName() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext_dao.xml");
        UserDao userDao = applicationContext.getBean(UserDao.class);
        List<User> userList = userDao.findByName("张");
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void testInsertUser() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext_dao.xml");
        UserDao userDao = applicationContext.getBean(UserDao.class);
        User user = new User();
        user.setUsername("欧阳锋");
        user.setBirthday(new Date());
        user.setSex("2");
        user.setAddress("深圳市");
        userDao.InsertUser(user);
    }

}
