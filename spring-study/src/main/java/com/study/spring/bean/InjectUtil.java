package com.study.spring.bean;

import com.study.spring.anno.InjectionByName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Modifier;

/**
 * @description: inject util
 * create: 2019-09-23 16:46
 * @author: yj
 **/
public class InjectUtil {

    private static final Logger logger = LoggerFactory.getLogger(InjectUtil.class);

    /**
     * 主要逻辑: 检查配置了InjectionByName的组件属性，并配置依赖关系, 在DefaultListableBeanFactory#appliyProperties()完成依赖注入
     * 限制: 只能根据bean的名称进行注入，且被注入的属性要有setter方法
     * */
    public static void injectByName(PropertyValues pvs, Object bean, String beanName) {
        ReflectionUtils.doWithLocalFields(bean.getClass(), field -> {
            InjectionByName injectionByName = field.getDeclaredAnnotation(InjectionByName.class);
            if(injectionByName != null) {
                if (Modifier.isStatic(field.getModifiers())) {
                    logger.info("@MyInjection annotation is not supported on static fields: " + field);
                } else {
                    if(pvs instanceof MutablePropertyValues) {
                        MutablePropertyValues mpvs = (MutablePropertyValues)pvs;
                        String refName = injectionByName.value();
                        if(StringUtils.isEmpty(refName)) {
                            refName = field.getName();
                        }
                        mpvs.add(field.getName(), new RuntimeBeanReference(refName));
                    } else {
                        logger.error("PropertyValues not writable, cannot execute inject logic for bean: {}", beanName);
                    }
                }
            }
        });
    }
}
