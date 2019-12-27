package com.study.security.service;

import com.study.security.dao.UserMapper;
import com.study.security.entity.User;
import com.study.security.entity.UserExample;

public interface UserService extends BaseService<User, UserExample, Long, UserMapper> {

    User queryByUsernameAndPassword(String username, String  password);
}
