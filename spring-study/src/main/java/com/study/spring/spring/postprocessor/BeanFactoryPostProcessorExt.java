package com.study.spring.spring.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class BeanFactoryPostProcessorExt implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println("BeanFactoryPostProcessorExt#postProcessBeanFactory() running");
    }

    public BeanFactoryPostProcessorExt() {
        System.out.println("BeanFactoryPostProcessorExt 实例化");
    }
}
