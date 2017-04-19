package com.learn.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Created by pengfei on 2017/3/19.
 */
@ControllerAdvice(basePackages="com.learn.web")
public class ControllerAdviceContoller {

    private final Log log = LogFactory.getLog(this.getClass()) ;
    private @Autowired  CommentValidator validator ;

    @ModelAttribute
    public String getComment(){
        String str = "k1sdfndjsfk2kdfghk3" ;
        log.info("modelAttribute=========" + str);
        return str ;
    }

    @InitBinder
    public void ininBinder(WebDataBinder binder){
        binder.setValidator(validator);
    }



}
