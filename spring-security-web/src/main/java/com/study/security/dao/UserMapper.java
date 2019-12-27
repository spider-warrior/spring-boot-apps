package com.study.security.dao;


import com.study.security.entity.User;
import com.study.security.entity.UserExample;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User, UserExample, Long> {
    User selectUserByUsername(@Param("username") String username);
    User selectUserByUsernameAndPassword(@Param("username") String username, @Param("password")String password);
}
