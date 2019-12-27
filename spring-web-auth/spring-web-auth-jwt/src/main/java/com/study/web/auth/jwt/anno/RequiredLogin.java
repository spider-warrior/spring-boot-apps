package com.study.web.auth.jwt.anno;


import com.study.web.auth.jwt.constants.ErrorCode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiredLogin {
    ErrorCode errorCode() default ErrorCode.CHECK_NO_USER_LOGIN;
    boolean checkAuth() default false;
    String operationName() default "未定义";
}
