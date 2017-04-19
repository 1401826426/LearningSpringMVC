package com.learn.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by pengfei on 2017/4/2.
 */
@Component
public  class CommentValidator implements Validator {

    private final Log log = LogFactory.getLog(this.getClass()) ;

    public boolean supports(Class<?> clazz) {
        return String.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {
        log.info("validator================" + target);
    }
}
