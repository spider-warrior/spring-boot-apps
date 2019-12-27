package com.study.spring.config;

import com.study.spring.spring.postprocessor.BeanFactoryPostProcessorExt;
import com.study.spring.spring.postprocessor.BeanPostProcessorExt;
import com.study.spring.spring.postprocessor.InstantiationAwareBeanPostProcessorExt;
import com.study.spring.spring.postprocessor.MergedBeanDefinitionPostProcessorExt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    BeanFactoryPostProcessorExt beanFactoryPostProcessorExt() {
        return new BeanFactoryPostProcessorExt();
    }

    @Bean
    BeanPostProcessorExt beanPostProcessorExt() {
        return new BeanPostProcessorExt();
    }

    @Bean
    InstantiationAwareBeanPostProcessorExt instantiationAwareBeanPostProcessorExt() {
        return new InstantiationAwareBeanPostProcessorExt();
    }

    @Bean
    MergedBeanDefinitionPostProcessorExt mergedBeanDefinitionPostProcessorExt() {
        return new MergedBeanDefinitionPostProcessorExt();
    }

    public AppConfig() {
        System.out.println("AppConfig 实例化");
    }
}
