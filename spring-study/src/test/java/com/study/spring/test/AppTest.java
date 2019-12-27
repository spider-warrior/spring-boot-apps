package com.study.spring.test;

import com.study.spring.SpringStudyApplication;
import com.study.spring.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description: this is for test
 * create: 2019-09-23 15:43
 * @author: yj
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes={SpringStudyApplication.class})// 指定启动类
public class AppTest {

    @Autowired
    private TestService testService;

    @Test
    public void testServiceTest() {
        System.out.println("testService: " + testService);
    }
}
