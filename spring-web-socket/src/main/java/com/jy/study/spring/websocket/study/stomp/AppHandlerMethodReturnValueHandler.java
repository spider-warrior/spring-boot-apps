package com.jy.study.spring.websocket.study.stomp;

import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.support.SendToMethodReturnValueHandler;

/**
 * @author yj
 * @since 2019-12-09 21:31
 **/
public class AppHandlerMethodReturnValueHandler extends SendToMethodReturnValueHandler {
    public AppHandlerMethodReturnValueHandler(SimpMessageSendingOperations messagingTemplate, boolean annotationRequired) {
        super(messagingTemplate, annotationRequired);
    }

}
