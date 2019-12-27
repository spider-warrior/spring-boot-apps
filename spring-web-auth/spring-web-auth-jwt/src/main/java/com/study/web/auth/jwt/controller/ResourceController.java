package com.study.web.auth.jwt.controller;

import com.study.web.auth.jwt.anno.RequiredLogin;
import com.study.web.auth.jwt.controller.response.wrapper.ResultVoWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 资源控制器
 *
 * @author yj
 * @since 2019-12-03 17:59
 **/
@RequestMapping("resources")
@RestController
public class ResourceController {

    private ResultVoWrapper resultVoWrapper;

    @RequiredLogin
    @GetMapping("protected")
    public Object getProtectedResource() {
        return resultVoWrapper.buildSuccess();
    }

    @GetMapping("unprotected")
    public Object getUnprotectedResource() {
        return resultVoWrapper.buildSuccess();
    }

    public ResourceController(ResultVoWrapper resultVoWrapper) {
        this.resultVoWrapper = resultVoWrapper;
    }
}
