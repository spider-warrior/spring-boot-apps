package com.study.security.controller;

import org.springframework.validation.FieldError;

public class BaseController {

    protected static final FieldError UNKNOWN_VALIDATION_ERROR = new FieldError("internal", "default", "未知");

}
