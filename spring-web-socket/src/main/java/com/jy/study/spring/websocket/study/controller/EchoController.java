package com.jy.study.spring.websocket.study.controller;

import com.jy.study.spring.websocket.study.helper.SecurityHelper;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

/**
 * @author yj
 * @since 2019-12-09 14:38
 **/
@Controller
public class EchoController {
    private SecurityHelper securityHelper;
    /**
     * 自言自语
     * */
    @SendToUser(value = "/topic/p2p", broadcast = false)
    @MessageMapping("echo")
    public String echo(String message) {
        return message;
    }

    public EchoController(SecurityHelper securityHelper) {
        this.securityHelper = securityHelper;
    }
}
