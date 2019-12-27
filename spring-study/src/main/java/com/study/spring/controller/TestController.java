package com.study.spring.controller;

import com.study.spring.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("test")
@RestController
public class TestController {

    private TestService testService;

    @GetMapping
    public String get() {
        return "get: test";
    }

    public TestController() {
        System.out.println("TestController 实例化");
    }

    public TestService getTestService() {
        return testService;
    }

    @Autowired
    public void setTestService(TestService testService) {
        System.out.println("TestController#setter注入");
        this.testService = testService;
    }
}
