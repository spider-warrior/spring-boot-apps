package com.jy.study.springcloud.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ConfigServer1 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServer1.class, args);
    }
}
