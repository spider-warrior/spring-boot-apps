package com.study.mvc.webflux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyHandler {

    @GetMapping("my")
    public Object myHandler() {
        if(true) {
            throw new NullPointerException();
        }
        return "it is me!";
    }
}
