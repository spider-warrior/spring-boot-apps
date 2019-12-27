package com.study.test.web.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@Validated
@RequestMapping("test")
@RestController
public class TestController {

    @PostMapping
    public void doReceiveNotify(@RequestBody String param) {
        System.out.println(param);
    }

    @GetMapping("binding-test")
    public void bindingTest(@NotNull(message = "id cannot be null") @RequestParam(value = "id", required = false) Long id) {
        System.out.println("id: " + id);
    }


}
