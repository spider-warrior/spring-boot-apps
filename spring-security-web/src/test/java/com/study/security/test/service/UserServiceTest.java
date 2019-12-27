package com.study.security.test.service;

import com.study.security.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void queryByUsernameAndPasswordTest() {
        String username = "aaa";
        String password = "123";
        System.out.println(userService.queryByUsernameAndPassword(username, password));
    }

}
