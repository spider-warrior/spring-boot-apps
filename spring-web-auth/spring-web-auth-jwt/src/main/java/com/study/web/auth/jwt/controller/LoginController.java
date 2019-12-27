package com.study.web.auth.jwt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.study.web.auth.jwt.controller.response.wrapper.ResultVoWrapper;
import com.study.web.auth.jwt.entity.User;
import com.study.web.auth.jwt.util.AppContextUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 登陆控制器
 *
 * @author yj
 * @since 2019-12-03 16:36
 **/
@RequestMapping("login")
@RestController
public class LoginController {

    private ResultVoWrapper resultVoWrapper;

    @PostMapping
    public Object login(@RequestParam("username") String username, @RequestParam("password") String password) throws JsonProcessingException {
        User user = new User();
        user.setId(1L);
        user.setUsername(username);
        user.setPassword(password);
        String jwt = AppContextUtil.generateJwt(user);
        Map<String, Object> data = new HashMap<>();
        data.put("jwt", jwt);
        return resultVoWrapper.buildSuccess(data);
    }

    public LoginController(ResultVoWrapper resultVoWrapper) {
        this.resultVoWrapper = resultVoWrapper;
    }
}
