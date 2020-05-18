package com.jy.study.spring.study.mybatis.config;

import cn.t.common.mybatis.idgenerator.RedisIdGenInterceptor;
import cn.t.common.mybatis.idgenerator.RedisKeyGenerator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.ValueOperations;

@MapperScan(basePackages="com.jy.study.spring.study.mybatis.mapper")
@Configuration
public class MybatisConfig {

    @Bean
    RedisIdGenInterceptor redisIdGenInterceptor(ValueOperations<String, String> valueOperations) {
        return new RedisIdGenInterceptor(new RedisKeyGenerator(valueOperations));
    }

}
