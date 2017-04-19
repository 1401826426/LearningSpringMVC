package com.learn.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by fei on 2016/9/17.
 */
@Controller
public class FirstController implements EnvironmentAware {

    private final Log logger = LogFactory.getLog(FirstController.class) ;

    @RequestMapping(value = "/index" , method = RequestMethod.GET)
    public String index(Model model){
//        Collections.unmodifiableMap(new HashMap()) ;

        model.addAttribute("END" , "Test") ;
        return "index" ;
    }

    private Environment environment = null ;



    public void setEnvironment(Environment environment) {
        this.environment = environment ;
    }
}
