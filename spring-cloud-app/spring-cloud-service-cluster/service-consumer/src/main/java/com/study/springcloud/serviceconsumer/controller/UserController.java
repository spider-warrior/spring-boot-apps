package com.study.springcloud.serviceconsumer.controller;

import cn.t.base.common.response.ResultVoWrapper;
import cn.t.base.common.service.ErrorInfo;
import cn.t.util.common.RandomUtil;
import cn.t.util.web.RequestUtil;
import com.google.common.base.Objects;
import com.jy.study.springcloud.serviceremote.User;
import com.study.springcloud.serviceconsumer.controller.param.LoginParam;
import com.study.springcloud.serviceconsumer.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

@RequestMapping("user")
@RestController
public class UserController {

    private final ResultVoWrapper voWrapper;
    private final UserService userService;

    @PostMapping("login")
    public Object login(@Valid LoginParam loginParam, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        User user = userService.getUserByUsername(loginParam.getUsername());
        if(user == null) {
            return voWrapper.buildFail(new ErrorInfo("404", "用户不存在"));
        }
        if(!Objects.equal(user.getPassword(), loginParam.getPassword())) {
            return voWrapper.buildFail(new ErrorInfo("401", "密码不正确"));
        }
        RequestUtil.setCookie(response, request.getServerName(), "token", RandomUtil.randomString(16) , 60 * 30);
        return voWrapper.buildSuccess("ok");
    }

    public UserController(ResultVoWrapper voWrapper, UserService userService) {
        this.voWrapper = voWrapper;
        this.userService = userService;
    }
}
