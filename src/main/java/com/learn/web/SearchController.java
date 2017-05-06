package com.learn.web;

import com.learn.service.SearchService;
import com.learn.util.Response;
import com.learn.util.ResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List ;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by pengfei on 2017/4/28.
 */
@Controller
@RequestMapping("/search")
public class SearchController {

    private static final Logger log = LoggerFactory.getLogger(SearchController.class) ;
    @Autowired private SearchService searchService ;

    /**
     * @api {post} /search/sift  通过sift算法检索图像
     * @apiParam {File} image  图像
     * @apiGroup search
     * @param image
     * @return
     */
    @RequestMapping(value = "/sift" , method = RequestMethod.POST)
    @ResponseBody
    public Response search(@RequestParam("image") MultipartFile image){
        try {
            return new Response(ResponseStatus.SUCCESS , "成功"  , searchService.siftSearch(image)) ;
        } catch (IOException e) {
            log.error("检索出错" , e);
            return new Response(ResponseStatus.SUCCESS , "内部出错") ;
        }
    }

}















