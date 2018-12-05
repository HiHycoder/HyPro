package cn.itheima.crm.mapper;

import cn.itheima.crm.pojo.User;
import cn.itheima.crm.pojo.UserExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 新建测试类代码生成
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml") //注入映射文件
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert() {
        User user = new User();
        user.setUsername("aaaaaa");
        userMapper.insert(user);
    }

    @Test
    public void selectByExample() {
        User user = userMapper.selectByPrimaryKey(1);
        System.out.println(user);
    }

    // 以Selective结尾的方法，在执行SQL的时候，进行了为空的判断
    // 没有Selective结尾的方法，在执行SQL的时候，没有进行为空的判断
    @Test
    public void selectByPrimaryKey() {
        //Example类就是用来构建查询条件
        // 如果要构建多个查询条件，一直使用criteria添加条件即可
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();

        criteria.andUsernameLike("%张%");
        criteria.andIdEqualTo(24);

        List<User> userList = userMapper.selectByExample(userExample);
        for (User user : userList) {
            System.out.println(user);
        }
    }
}