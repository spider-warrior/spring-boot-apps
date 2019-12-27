package com.study.security.controller;

import com.study.security.entity.User;
import com.study.security.response.wrapper.ResultVoWrapper;
import com.study.security.response.wrapper.UserVoWrapper;
import com.study.security.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.study.security.constants.ErrorCode.FORM_ERROR;
import static com.study.security.constants.ErrorCode.USER_NOT_EXIST;

@RequestMapping("user")
@RestController
public class UserController extends BaseController {

    private UserService userService;
    private ResultVoWrapper resultVoWrapper;
    private UserVoWrapper userVoWrapper;

    private AuthenticationManager authenticationManager;
    private SecurityContextRepository repository;
    private RememberMeServices rememberMeServices;

    @PostMapping("login")
    public Object login(@Valid @RequestBody LoginParam loginParam, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
        if(bindingResult.hasErrors()) {
            return resultVoWrapper.buildFail(FORM_ERROR.value, Optional.ofNullable(bindingResult.getFieldError()).orElse(UNKNOWN_VALIDATION_ERROR).getDefaultMessage());
        }
        User user = userService.queryByUsernameAndPassword(loginParam.getUsername(), loginParam.getPassword());
        if(user == null) {
            return resultVoWrapper.buildFail(USER_NOT_EXIST);
        }
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginParam.getUsername(), loginParam.getPassword());
        Authentication auth = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(auth);
        repository.saveContext(SecurityContextHolder.getContext(), request, response);
        rememberMeServices.loginSuccess(request, response, auth);

        Map<String, Object> data = new HashMap<>();
        data.put("user", userVoWrapper.buildUserVo(user));
        return resultVoWrapper.buildSuccess().setData(data);
    }

    public UserController(UserService userService, ResultVoWrapper resultVoWrapper, UserVoWrapper userVoWrapper, AuthenticationManager authenticationManager, SecurityContextRepository repository, RememberMeServices rememberMeServices) {
        this.userService = userService;
        this.resultVoWrapper = resultVoWrapper;
        this.userVoWrapper = userVoWrapper;
        this.authenticationManager = authenticationManager;
        this.repository = repository;
        this.rememberMeServices = rememberMeServices;
    }
}

class LoginParam {

    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
