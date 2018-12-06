package com.ebuy.springbootmp.web;

import java.util.Date;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ebuy.springbootmp.entity.User;
import com.ebuy.springbootmp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hy
 * @since 2018-12-06
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;


    //单表
    @GetMapping("/show")
    public JSONObject testSelectPage() {
        Page<User> userPage = userService.selectPage(new Page<User>(1, 1));
        JSONObject result = new JSONObject();
        result.put("users", userPage);
        System.out.println("总记录数:" + userPage.getTotal());
        System.out.println("总页数:" + userPage.getPages());
        return result;
    }

    /**
     * 多表查询 -- 分页
     *
     * @return
     */
    @GetMapping("/queryList")
    public Object queryList() {
        Page<Map> userPage = userService.queryList(1, 2, new HashMap<String, Object>());
        return userPage;
    }


    @GetMapping("/add")
    public void add() {
        User user = new User();
        user.setUserId("hhhhh");
        user.setUserName("344");
        user.setUserAge(22);
        user.setBirthday(new Date());
        user.setPhoneNum(21);
        user.setAddress("北京市");
        user.setRemark("");
        userService.insert(user);
    }

    @GetMapping("/edit")
    public void edit() {
        User user = userService.selectById(4);
        user.setUserName("5645454545");
        userService.updateById(user);
    }

    /**
     * 条件查询 -- 用到wrapper的实现类entityWrapper
     *
     * @return
     */
    @GetMapping("/find")
    public Object find() {

        EntityWrapper<User> wrapper = new EntityWrapper<User>();
        wrapper.eq("userId", "qeqweqw");
        wrapper.like("username", "hh");
        List<User> list = userService.selectList(wrapper);
        //User user = userService.selectById(4);
        return list;
    }


    @GetMapping("/delete")
    public void deleteById() {
        userService.deleteById(4);
    }
}
