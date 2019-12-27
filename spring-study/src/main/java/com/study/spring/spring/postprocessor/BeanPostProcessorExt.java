package com.study.spring.spring.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class BeanPostProcessorExt implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("BeanPostProcessorExt#postProcessBeforeInitialization() running， bean name: " + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("BeanPostProcessorExt#postProcessAfterInitialization() running， bean name: " + beanName);
        return bean;
    }

    public BeanPostProcessorExt() {
        System.out.println("BeanPostProcessorExt 实例化");
    }
}
