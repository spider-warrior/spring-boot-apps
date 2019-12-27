package com.study.spring.spring.afterstartup;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 可根据Order排序执行顺序，值越小，越优先执行，，run方法会阻塞后面程序的执行
 * */
@Order(value = 1)
@Component
public class CommandLineRunnerExt implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("CommandLineRunnerExt#run() running");
    }

    public CommandLineRunnerExt() {
        System.out.println("CommandLineRunnerExt 实例化");
    }
}
