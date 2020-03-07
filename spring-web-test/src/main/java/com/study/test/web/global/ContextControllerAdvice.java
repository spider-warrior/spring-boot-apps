package com.study.test.web.global;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.validator.engine.HibernateConstraintViolation;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestControllerAdvice
public class ContextControllerAdvice {

    private final Logger logger = LogManager.getLogger(ContextControllerAdvice.class);



    /**
     * 400
     * */
    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String, Object> noHandlerFound(ConstraintViolationException e) {
        logger.error("cat a ConstraintViolationException", e);
        Set<ConstraintViolation<?>> errors = e.getConstraintViolations();
        Map<String, Object> result = new HashMap<>();
        result.put("code", "500");
        if(!CollectionUtils.isEmpty(errors)) {
            Object defaultError = errors.toArray()[0];
            if(defaultError instanceof HibernateConstraintViolation) {
                HibernateConstraintViolation<?> validation = (HibernateConstraintViolation<?>)defaultError;
                result.put("msg", validation.getMessage());
            }
        }
        return result;
    }



}
