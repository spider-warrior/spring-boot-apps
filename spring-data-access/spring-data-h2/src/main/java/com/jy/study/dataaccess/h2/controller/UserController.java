package com.jy.study.dataaccess.h2.controller;

import com.jy.study.dataaccess.h2.domain.User;
import com.jy.study.dataaccess.h2.service.UserService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("user")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping
    public String createUser(@RequestBody User user) {
        userService.create(user);
        return "success";
    }

    @GetMapping("{id:\\d+}")
    public User getUser(@PathVariable("id") Long id) {
        return userService.queryById(id);
    }

    public UserController(UserService userService) {
        this.userService = userService;
    }
}
