package com.study.springcloud.serviceconsumer.test;

import com.study.springcloud.serviceconsumer.ServiceConsumerServer;
import com.study.springcloud.serviceconsumer.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceConsumerServer.class)
public class AppTest {

    @Autowired
    private UserService userService;

    @Test
    public void getUserByIdTest() {
        System.out.println(userService.getUserById(10000L));
    }

    @Test
    public void getUserByIdWithFeignTest() {
        System.out.println(userService.getUserByIdWithFeign(10000L));
    }

}
