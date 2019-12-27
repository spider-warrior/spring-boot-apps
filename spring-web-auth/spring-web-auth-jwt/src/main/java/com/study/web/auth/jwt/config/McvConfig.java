package com.study.web.auth.jwt.config;

import com.study.web.auth.jwt.controller.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * mvc config
 *
 * @author yj
 * @since 2019-12-03 17:42
 **/
@Configuration
public class McvConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor());
    }

    @Bean
    LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }
}
