package com.study.springcloud.serviceprovider.controller;

import com.jy.study.springcloud.serviceremote.User;
import com.jy.study.springcloud.serviceremote.UserServiceApi;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements UserServiceApi {

    @Override
    public User getUserById(Long id) {
        User user = new User();
        user.setId(id);
        return user;
    }
}
