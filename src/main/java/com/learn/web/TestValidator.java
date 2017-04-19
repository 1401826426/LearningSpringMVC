package com.learn.web;

import com.learn.entity.User;
import com.learn.validatos.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by pengfei on 2017/3/14.
 */
@Controller
public class TestValidator {

    @Autowired  private UserValidator validator ;

    @InitBinder
    private void initBinder(WebDataBinder binder){
        binder.setValidator(validator);
    }


    @RequestMapping(value = "/valid" , method = RequestMethod.POST)
    @ResponseBody
    public User validUser(@Validated User user){
        return user ;
    }
}
