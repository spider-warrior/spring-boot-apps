package com.jy.study.spring.websocket.study.stomp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.messaging.SubProtocolWebSocketHandler;

/**
 * @author yj
 * @since 2019-12-10 16:03
 **/
public class AppSubProtocolWebSocketHandler extends SubProtocolWebSocketHandler {

    private static final Logger logger = LoggerFactory.getLogger(AppSubProtocolWebSocketHandler.class);

    public AppSubProtocolWebSocketHandler(MessageChannel clientInboundChannel, SubscribableChannel clientOutboundChannel) {
        super(clientInboundChannel, clientOutboundChannel);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("=================> a new connection establish");
        super.afterConnectionEstablished(session);
    }
}
