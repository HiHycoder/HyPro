package com.ebuy.springbootmp.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.ebuy.springbootmp.entity.User;
import com.ebuy.springbootmp.mapper.UserMapper;
import com.ebuy.springbootmp.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @since 2018-12-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;


    @Transactional(readOnly = true)
    @Override
    public Page<Map> queryList(int page, int size, Map<String, Object> map) {

        Page<Map> mapPage = new Page<Map>(1,2);
        map.put("age",13);
        List<Map> myItems = userMapper.queryList(mapPage,map);
        //page1.setRecords(this.userMapper.queryList(page1, map));
        mapPage.setRecords(myItems);
        return mapPage;
    }
}
