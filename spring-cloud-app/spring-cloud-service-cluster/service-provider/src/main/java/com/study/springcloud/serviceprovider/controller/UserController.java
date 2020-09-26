package com.study.springcloud.serviceprovider.controller;

import com.jy.study.springcloud.serviceremote.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("user")
@RestController
public class UserController {

    @GetMapping("{id:\\d+}")
    public User userDetail(@PathVariable("id") Long id) {
        User user = new User();
        user.setId(id);
        return user;
    }
}
