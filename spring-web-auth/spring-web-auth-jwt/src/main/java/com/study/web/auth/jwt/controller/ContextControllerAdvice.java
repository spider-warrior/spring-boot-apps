package com.study.web.auth.jwt.controller;

import cn.t.util.common.CollectionUtil;
import com.study.web.auth.jwt.constants.ErrorCode;
import com.study.web.auth.jwt.controller.response.vo.ResultVo;
import com.study.web.auth.jwt.controller.response.wrapper.ResultVoWrapper;
import com.study.web.auth.jwt.exception.ServiceException;
import io.jsonwebtoken.MalformedJwtException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.validator.engine.HibernateConstraintViolation;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.CollectionUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

import static com.study.web.auth.jwt.constants.ErrorCode.CHECK_NO_USER_LOGIN;

@RestControllerAdvice
public class ContextControllerAdvice {

    private final Logger logger = LogManager.getLogger(ContextControllerAdvice.class);

    private ResultVoWrapper resultVoWrapper;


    @ExceptionHandler(MalformedJwtException.class)
    public ResultVo malformedJwtException(MalformedJwtException malformedJwtException) {
        return resultVoWrapper.buildFail(CHECK_NO_USER_LOGIN);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResultVo noHandlerFound(ConstraintViolationException e) {
        logger.error("cat a ConstraintViolationException", e);
        Set<ConstraintViolation<?>> errors = e.getConstraintViolations();
        if(!CollectionUtils.isEmpty(errors)) {
            Object defaultError = errors.toArray()[0];
            if(defaultError instanceof HibernateConstraintViolation) {
                HibernateConstraintViolation validation = (HibernateConstraintViolation)defaultError;
                ErrorCode errorCode = ErrorCode.valueOf(validation.getMessage());
                if(errorCode == null) {
                    errorCode = ErrorCode.SERVER_INTERNAL_EXCEPTION;
                }
                return resultVoWrapper.buildFail(errorCode);
            }
        }
        return resultVoWrapper.buildFail(ErrorCode.SERVER_INTERNAL_EXCEPTION);
    }

    /**
     * 400
     * */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResultVo noHandlerFound(HttpMessageNotReadableException e) {
        logger.error("cat a HttpMessageNotReadableException", e);
        return resultVoWrapper.buildFail(ErrorCode.BAD_PARAM_EXCEPTION);
    }

    /**
     * 404
     */

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResultVo noHandlerFound(NoHandlerFoundException e) {
        logger.error("cat a NoHandlerFoundException", e);
        return resultVoWrapper.buildFail(ErrorCode.SOURCE_NOT_FOUND_EXCEPTION);
    }


    /**
     * 405
     * */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResultVo methodNotSupport(HttpRequestMethodNotSupportedException e) {
        logger.error("cat a NoHandlerFoundException", e);
        return resultVoWrapper.buildFail(ErrorCode.REQUEST_METHOD_NOT_SUPPORT);
    }

    /**
     * 500
     */
    @ExceptionHandler(Throwable.class)
    public ResultVo exception(Throwable t) {
        if(t instanceof ServiceException) {
            ServiceException serviceException = (ServiceException)t;
            ErrorCode errorCode = serviceException.getErrorCode();
            if(errorCode != null) {
                logger.info("业务异常, code: {}, msg: {}, data: {}", errorCode.code, errorCode.msg, serviceException.getData());
                ResultVo resultVo = resultVoWrapper.buildFail(serviceException.getErrorCode());
                if(!CollectionUtil.isEmpty(serviceException.getData())) {
                    resultVo.setData(serviceException.getData());
                }
                return resultVo;
            } else {
                logger.error("抛出的业务异常没有错误码", serviceException);
                return resultVoWrapper.buildFail(ErrorCode.SERVER_INTERNAL_EXCEPTION);
            }
        } else {
            logger.error("catch a exception", t);
            return resultVoWrapper.buildFail(ErrorCode.SERVER_INTERNAL_EXCEPTION);
        }
    }

    public ContextControllerAdvice(ResultVoWrapper resultVoWrapper) {
        this.resultVoWrapper = resultVoWrapper;
    }
}
