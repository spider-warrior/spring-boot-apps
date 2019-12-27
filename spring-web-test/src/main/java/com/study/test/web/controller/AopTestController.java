package com.study.test.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yj
 * @since 2019-11-15 14:41
 **/
@RequestMapping("aop-test")
@RestController
public class AopTestController {

    @GetMapping
    public Object test(@RequestParam boolean throwException) throws Exception {
        // case 1
        if (throwException) {
            System.out.println("throw an exception");
            throw new Exception("mock a server exception");
        }

        // case 2
        System.out.println("test OK");
        return "success";
    }

}
