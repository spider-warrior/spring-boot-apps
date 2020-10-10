package com.study.springcloud.serviceconsumer.service;

import com.jy.study.springcloud.serviceremote.User;

public interface UserService {
    User getUserById(Long id);
    User getUserByIdWithFeign(Long id);
    User getUserByUsername(String username);
}
