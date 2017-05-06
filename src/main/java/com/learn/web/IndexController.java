package com.learn.web;

import org.springframework.stereotype.Controller;

/**
 * Created by pengfei on 2017/4/22.
 */
@Controller
public class IndexController {

//    @RequestMapping("/")
    public String index(){
        return "index" ;
    }

}
