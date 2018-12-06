package com.ebuy.springbootmp.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.ebuy.springbootmp.entity.User;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hy
 * @since 2018-12-06
 */
public interface IUserService extends IService<User> {

    public Page<Map> queryList(int page , int size, Map<String,Object> map);

}
