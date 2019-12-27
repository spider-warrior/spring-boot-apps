package com.study.spring.spring.postprocessor;

import com.study.spring.anno.InjectionByName;
import com.study.spring.bean.InjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Modifier;

public class InstantiationAwareBeanPostProcessorExt implements InstantiationAwareBeanPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(InstantiationAwareBeanPostProcessorExt.class);

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessorExt#postProcessBeforeInstantiation() running， bean name: " + beanName);
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessorExt#postProcessAfterInstantiation() running， bean name: " + beanName);
        return true;
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessorExt#postProcessProperties() running， bean name: " + beanName);
        InjectUtil.injectByName(pvs, bean, beanName);
        return pvs;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessorExt#postProcessBeforeInitialization() running， bean name: " + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessorExt#postProcessAfterInitialization() running， bean name: " + beanName);
        return bean;
    }

    public InstantiationAwareBeanPostProcessorExt() {
        System.out.println("InstantiationAwareBeanPostProcessorExt 实例化");
    }
}
