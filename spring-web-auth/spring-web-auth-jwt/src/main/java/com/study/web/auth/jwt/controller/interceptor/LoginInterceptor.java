package com.study.web.auth.jwt.controller.interceptor;

import com.study.web.auth.jwt.anno.RequiredLogin;
import com.study.web.auth.jwt.constants.ErrorCode;
import com.study.web.auth.jwt.entity.User;
import com.study.web.auth.jwt.exception.ServiceException;
import com.study.web.auth.jwt.util.AppContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 登陆拦截器
 *
 * @author yj
 * @since 2019-12-03 16:39
 **/
@Slf4j
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod) {
            Method method = ((HandlerMethod) handler).getMethod();
            User user = checkUserLogin(request, method);
            if(user != null) {
                AppContextUtil.setCurrentUser(user);
            }
        }
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        AppContextUtil.removeCurrentUser();
    }

    private User checkUserLogin(HttpServletRequest request, Method method) {
        RequiredLogin requiredLogin = method.getAnnotation(RequiredLogin.class);
        if(requiredLogin == null) {
            requiredLogin = method.getDeclaringClass().getAnnotation(RequiredLogin.class);
        }
        if (requiredLogin != null) {
            String uri = request.getRequestURI();
            log.info("[{}]需要[登陆]验证,即将进行验证", uri);
            String authorization = request.getHeader("Authorization");
            if (authorization == null || authorization.length() < 8) {
                throw new ServiceException(ErrorCode.CHECK_NO_USER_LOGIN, null);
            } else {
                User user = AppContextUtil.getUserByJwt(authorization.substring(7));
                if (user == null) {
                    throw new ServiceException(ErrorCode.CHECK_NO_USER_LOGIN, null);
                } else {
                    log.info("[{}]需要登陆验证, 验证通过, 用户: {}", uri, user.getUsername());
                    return user;
                }
            }
        } else {
            return null;
        }
    }
}
