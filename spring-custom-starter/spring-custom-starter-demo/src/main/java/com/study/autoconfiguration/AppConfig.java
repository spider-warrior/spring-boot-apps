package com.study.autoconfiguration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = "com.study.autoconfiguration")
@Configuration
public class AppConfig {
}
