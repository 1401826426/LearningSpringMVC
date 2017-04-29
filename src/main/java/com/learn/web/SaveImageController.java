package com.learn.web;

import com.learn.service.SaveImageSerive;
import com.learn.util.Response;
import com.learn.util.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * Created by pengfei on 2017/4/28.
 */
@Controller
public class SaveImageController {

    @Autowired private SaveImageSerive saveImageSerive ;

    @RequestMapping("/save")
    @ResponseBody
    public Response save(@RequestParam("num") int num , @RequestParam("pos") String pos){
        try {
            saveImageSerive.save(num , pos) ;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Response(ResponseStatus.SUCCESS , "存储成功") ;
    }

}
