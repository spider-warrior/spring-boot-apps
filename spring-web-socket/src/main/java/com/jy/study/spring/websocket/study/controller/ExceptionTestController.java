package com.jy.study.spring.websocket.study.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

/**
 * 异常测试控制器
 *
 * @author yj
 * @since 2019-12-05 20:55
 **/
@Controller
public class ExceptionTestController {

    @SendToUser(value = "/topic/p2p", broadcast = false)
    @MessageMapping("runtime-exception")
    public void runtimeException() {
        throw new RuntimeException();
    }

}
