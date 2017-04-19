package com.learn.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * Created by pengfei on 2017/3/14.
 */
@Controller
@RequestMapping("/book2")
@SessionAttributes(value={"book" , "description"} , types={Double.class})
public class TestSessionAttribute2 {

    private final Log log = LogFactory.getLog(this.getClass()) ;

    @RequestMapping("/index")
    @ResponseBody
    public String index(Model model){
        model.addAttribute("book" , "金刚经") ;
        model.addAttribute("description" , "般若系列经典")  ;
        model.addAttribute("price" , 998d) ;
        return model.toString() ;
    }


}
