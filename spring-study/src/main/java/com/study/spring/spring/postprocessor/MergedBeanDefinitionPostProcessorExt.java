package com.study.spring.spring.postprocessor;

import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * merge bean definition
 * create: 2019-09-23 14:12
 * @author yj
 **/
public class MergedBeanDefinitionPostProcessorExt implements MergedBeanDefinitionPostProcessor {
    @Override
    public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName) {
        System.out.println("MergedBeanDefinitionPostProcessorExt#postProcessMergedBeanDefinition() running, bean name: " + beanName);
    }

}
