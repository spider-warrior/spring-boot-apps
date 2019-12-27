package com.study.security.constants;

public enum ErrorCode {

    USER_NOT_EXIST("10000", "用户不存在"),


    FORM_ERROR("40000", "表单错误")

    ;
    public final String value;
    public final String description;

    ErrorCode(String value, String description) {
        this.value = value;
        this.description = description;
    }


}
