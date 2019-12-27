package com.study.test.web.aspect;

import org.aspectj.lang.annotation.Pointcut;

/**
 * @author yj
 * @since 2019-11-15 14:48
 **/
public class PointCuts {
    @Pointcut(value = "within(com.study.test.web.controller.AopTestController)")
    public void aopDemo() {
    }
}
