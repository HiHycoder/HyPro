package com.ebuy.springbootmp.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.ebuy.springbootmp.entity.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hy
 * @since 2018-12-06
 */
public interface UserMapper extends BaseMapper<User> {

    //注意  这里的pagination是必要的!
    List<Map> queryList(Page<Map> mapPage ,Map<String ,Object> map);

}
