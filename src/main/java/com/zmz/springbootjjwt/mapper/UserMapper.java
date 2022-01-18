package com.zmz.springbootjjwt.mapper;

import com.zmz.springbootjjwt.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author zhangshengrui
 * @date 2022-01-06 20:44
 */
public interface UserMapper {
    User findByUsername(@Param("username") String username);
    User findUserById(@Param("Id") String Id);
}
