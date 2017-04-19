package com.learn.validatos;

import com.learn.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by pengfei on 2017/3/14.
 */
@Component
public class UserValidator implements Validator {
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
        User user = (User)o ;
        if(user.getUsername() == null || user.getUsername().length() < 8){
//            errors.rejectValue("username" , "username的个数小于8");
        }
    }
}

























