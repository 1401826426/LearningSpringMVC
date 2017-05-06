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


    /**
     * @api /save  将数据库的图像文件存在对应的位置
     * @apiParam {Number} num 每种类型存的个数
     * @apiParam {String} pos 图像存的位置
     * @apiGroup save
     * @param num
     * @param pos
     * @return
     */
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
