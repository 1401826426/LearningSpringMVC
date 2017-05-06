package com.learn.web;

import com.learn.service.CrawService;
import com.learn.util.Response;
import com.learn.util.ResponseStatus;
import com.sun.javafx.collections.MappingChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by pengfei on 2017/4/24.
 */
@Controller
@RequestMapping("/craw")
public class ImageCrawlerController {

    @Autowired
    private CrawService crawService ;

    /**
     * @api {post}  /craw  爬取图片
     * @apiParam {String} url 爬取的url地址
     * @apiParam {String} type 爬取的类型
     * @apiGroup craw
     * @param url
     * @param type
     * @return
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public Response craw(@RequestParam("url") String url, @RequestParam("type") String type){
        return new Response(ResponseStatus.SUCCESS , "成功" ,crawService.craw(url , type)) ;
    }


    /**
     * @api {post}  /craw/v2  爬取图片
     * @apiParam {String} url 爬取的url地址
     * @apiParam {String} type 爬取的类型
     * @apiGroup craw
     * @param data
     * @return
     */
    @ResponseBody
    @RequestMapping(value =  "/v2" , method = RequestMethod.POST)
    public Response crawV2(@RequestBody Map<String,String> data){
        String url = data.get("url") ;
        if(url == null || "".equals(url)){
            return new Response(ResponseStatus.FAILURE , "没有url") ;
        }
        String type = data.get("type") ;
        if(type == null || "".equals(type)){
            return new Response(ResponseStatus.FAILURE , "没有type") ;
        }
        return new Response(ResponseStatus.SUCCESS , "成功" , crawService.craw(url,type)) ;

    }


}









