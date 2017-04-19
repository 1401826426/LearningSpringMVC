package com.learn.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

/**
 * Created by pengfei on 2017/3/13.
 */
@Controller
@RequestMapping("/book")
@SessionAttributes(value={"book" , "description"} , types={Double.class})
public class TestSessionAttribute {

    private final Log log = LogFactory.getLog(this.getClass()) ;

    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("book" , "金刚经") ;
        model.addAttribute("description" , "般若系列经典")  ;
        model.addAttribute("price" , 998d) ;
        return "redirect:get" ;
    }

    @RequestMapping("/get")
    public String get(@ModelAttribute("book") String book , ModelMap model , SessionStatus sessionStatus){
        log.info("===================================getBySessionAttribute==================================");
        log.info("get by @ModelAttribute  " +  book) ;
        log.info(model.get("book") + "   " + model.get("description") + "  " + model.get("price")) ;
        sessionStatus.setComplete();
        return "redirect:complete" ;
    }

    @RequestMapping("/complete")
    @ResponseBody
    public String complete(ModelMap model){
        log.info("=====================================================AfterComplete====================") ;
        log.info("book==" + model.get("book")) ;
        return  model.toString() ;
    }





}
