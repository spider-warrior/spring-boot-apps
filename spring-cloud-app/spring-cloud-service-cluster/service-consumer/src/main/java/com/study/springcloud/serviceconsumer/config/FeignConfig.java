package com.study.springcloud.serviceconsumer.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients(value = "com.jy.study.springcloud.serviceremote")
@Configuration
public class FeignConfig {
}
