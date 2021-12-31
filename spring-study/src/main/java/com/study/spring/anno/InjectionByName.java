package com.study.spring.anno;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 自定义注入
 * create: 2019-09-23 14:07
 * @author yj
 **/
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface InjectionByName {
    String value() default "";
}
