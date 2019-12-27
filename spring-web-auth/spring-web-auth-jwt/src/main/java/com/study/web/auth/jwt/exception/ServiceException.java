package com.study.web.auth.jwt.exception;

import com.study.web.auth.jwt.constants.ErrorCode;

import java.util.HashMap;
import java.util.Map;

public class ServiceException extends RuntimeException {

    private ErrorCode errorCode;

    private Map<String, Object> data = new HashMap<>();

    public ServiceException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ServiceException(ErrorCode errorCode, Map<String, Object> data) {
        this.errorCode = errorCode;
        this.data = data;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
