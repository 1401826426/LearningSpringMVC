package com.learn.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.ws.rs.Path;
import java.io.IOException;

/**
 * Created by pengfei on 2017/3/18.
 */
@Controller
@SessionAttributes("articleId")
public class FollowMeController {

    private final Log log = LogFactory.getLog(this.getClass()) ;
    private final String[] sensitiveWords = new String[]{"k1" , "k2"} ;

    @ModelAttribute("comment")
    public String replaceSensitiveWords(String comment) throws IOException{
        if(comment != null){
            log.info("original comment:" + comment);
            for(String sw:sensitiveWords){
                comment = comment.replaceAll(sw , "") ;
            }
        }
        return comment ;
    }

    @RequestMapping(value = "/articles/{articleId}/comment")
    public String doComment(@PathVariable String articleId , RedirectAttributes attributes , Model model){
        attributes.addFlashAttribute("comment" , model.asMap().get("comment")) ;
        model.addAttribute("articleId" , articleId) ;
        return "redirect:/showArticle" ;
    }

    @RequestMapping(value = "/showArticle")
    public String showArticle(Model model , SessionStatus sessionStatus){
        String articleId = (String)model.asMap().get("articleId") ;
        model.addAttribute("articleTitle" , articleId + "文章标题") ;
        model.addAttribute("article" , articleId + "文章内容") ;
        sessionStatus.setComplete();
        return "article" ;
    }


}
