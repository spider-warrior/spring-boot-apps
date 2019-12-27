package com.jy.study.spring.websocket.study.controller.global;

import com.jy.study.spring.websocket.study.controller.response.vo.ResultVo;
import com.jy.study.spring.websocket.study.controller.response.wrapper.ResultVoWrapper;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * controller全局异常处理器
 *
 * @author yj
 * @since 2019-12-06 12:45
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResultVoWrapper resultVoWrapper;

    /**
     * 500
     */
    @ExceptionHandler(Throwable.class)
    public ResultVo httpException(Throwable t) {
        return resultVoWrapper.buildFail();
    }

    public GlobalExceptionHandler(ResultVoWrapper resultVoWrapper) {
        this.resultVoWrapper = resultVoWrapper;
    }

    @SendToUser("/topic/echo")
    @MessageExceptionHandler
    public ResultVo messageException(Throwable t) {
        return resultVoWrapper.buildFail();
    }

}
