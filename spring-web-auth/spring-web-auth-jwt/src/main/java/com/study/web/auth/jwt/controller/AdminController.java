package com.study.web.auth.jwt.controller;

import com.study.web.auth.jwt.controller.response.wrapper.ResultVoWrapper;
import com.study.web.auth.jwt.util.AppContextUtil;
import org.springframework.web.bind.annotation.*;

/**
 * 管理控制器
 *
 * @author yj
 * @since 2019-12-03 18:16
 **/
@RequestMapping("admin")
@RestController
public class AdminController {

    private ResultVoWrapper resultVoWrapper;

    @DeleteMapping("clearUserLoginStatus/{id:\\d+}")
    public Object clearUserLoginStatus(@PathVariable("id") Long id) {
        AppContextUtil.invalidJwt(id);
        return resultVoWrapper.buildSuccess();
    }


    public AdminController(ResultVoWrapper resultVoWrapper) {
        this.resultVoWrapper = resultVoWrapper;
    }
}
