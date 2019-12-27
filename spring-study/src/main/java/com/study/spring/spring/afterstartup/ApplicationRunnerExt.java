package com.study.spring.spring.afterstartup;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 可根据Order排序执行顺序，值越小，越优先执行，run方法会阻塞后面程序的执行
 * */
@Order(value = 2)
@Component
public class ApplicationRunnerExt implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("ApplicationRunnerExt#run() running");
        try {  Thread.sleep(5000); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    public ApplicationRunnerExt() {
        System.out.println("ApplicationRunnerExt 实例化");
    }
}
