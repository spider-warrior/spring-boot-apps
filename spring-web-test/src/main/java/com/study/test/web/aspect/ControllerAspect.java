package com.study.test.web.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author yj
 * @since 2019-11-15 14:45
 **/

@Component
@Aspect
public class ControllerAspect {
    @Before(value = "com.study.test.web.aspect.PointCuts.aopDemo()")
    public void before(JoinPoint joinPoint) {
        System.out.println("[Aspect1] before advise");
    }

    @Around(value = "com.study.test.web.aspect.PointCuts.aopDemo()")
    public Object around(ProceedingJoinPoint pjp) throws  Throwable{
        System.out.println("[Aspect1] around advise 1");
        Object result = pjp.proceed();
        System.out.println("[Aspect1] around advise2");
        return result;
    }

    @AfterReturning(value = "com.study.test.web.aspect.PointCuts.aopDemo()")
    public void afterReturning(JoinPoint joinPoint) {
        System.out.println("[Aspect1] afterReturning advise");
    }

    @AfterThrowing(value = "com.study.test.web.aspect.PointCuts.aopDemo()")
    public void afterThrowing(JoinPoint joinPoint) {
        System.out.println("[Aspect1] afterThrowing advise");
    }

    @After(value = "com.study.test.web.aspect.PointCuts.aopDemo()")
    public void after(JoinPoint joinPoint) {
        System.out.println("[Aspect1] after advise");
    }
}
