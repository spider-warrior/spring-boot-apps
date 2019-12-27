package com.jy.study.spring.websocket.study.config;


import com.jy.study.spring.websocket.study.config.properties.AppProperties;
import com.jy.study.spring.websocket.study.handler.AuthorityCheckWebSocketAnnotationMethodMessageHandler;
import com.jy.study.spring.websocket.study.helper.SecurityHelper;
import com.jy.study.spring.websocket.study.stomp.AppHandlerMethodReturnValueHandler;
import com.jy.study.spring.websocket.study.stomp.AppSubProtocolWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.invocation.HandlerMethodReturnValueHandler;
import org.springframework.messaging.simp.annotation.support.SimpAnnotationMethodMessageHandler;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.DelegatingWebSocketMessageBrokerConfiguration;

import java.util.List;

@Configuration
public class AppDelegatingWebSocketMessageBrokerConfiguration extends DelegatingWebSocketMessageBrokerConfiguration {

    private SecurityHelper securityHelper;
    private AppProperties appProperties;

    @Override
    protected SimpAnnotationMethodMessageHandler createAnnotationMethodMessageHandler() {
        return new AuthorityCheckWebSocketAnnotationMethodMessageHandler(
            clientInboundChannel(), clientOutboundChannel(), brokerMessagingTemplate(), securityHelper, appProperties);
    }

    @Bean
    @Override
    public WebSocketHandler subProtocolWebSocketHandler() {
        return new AppSubProtocolWebSocketHandler(clientInboundChannel(), clientOutboundChannel());
    }

    @Bean
    public AppHandlerMethodReturnValueHandler appHandlerMethodReturnValueHandler() {
        return new AppHandlerMethodReturnValueHandler(brokerMessagingTemplate(), true);
    }

    public AppDelegatingWebSocketMessageBrokerConfiguration(SecurityHelper securityHelper,
                                                            AppProperties appProperties) {
        this.securityHelper = securityHelper;
        this.appProperties = appProperties;
    }

    @Override
    protected void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
        returnValueHandlers.add(appHandlerMethodReturnValueHandler());
        super.addReturnValueHandlers(returnValueHandlers);
    }

}
