package com.learn.web;

import com.learn.service.CommodityService;
import com.learn.util.Response;
import com.learn.util.ResponseStatus;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by pengfei on 2017/3/11.
 */
@Controller
@RequestMapping("/image")
public class ImageController {

    private @Autowired  Environment environment ;
    private @Autowired
    CommodityService imageService ;
    private Log log = LogFactory.getLog(this.getClass()) ;


    @RequestMapping(value = "/upload" , method = RequestMethod.POST)
    @ResponseBody
    public Response upload(@RequestParam("image")MultipartFile image , @RequestParam("category") String category){
        String fileName = image.getOriginalFilename() ;
        String suffix = fileName.substring(fileName.lastIndexOf('.')+1) ;
        if("jpg".compareToIgnoreCase(suffix) == 0 || "png".compareToIgnoreCase(suffix) == 0){
            try {
                String name  = "E:\\test\\" + fileName ;
                File file = new File(name) ;
                if(!file.exists())file.createNewFile() ;
                IOUtils.copy(image.getInputStream(), new FileOutputStream(file));
                return new Response(ResponseStatus.SUCCESS , "上传成功") ;
            }catch (IOException e){
                log.error(fileName + "上传失败" + e) ;
                return new Response(ResponseStatus.FAILURE , "上传失败,内部错误");
            }
        }else{
            return new Response(ResponseStatus.FAILURE , "上传失败,不是image类型");
        }
    }

    @RequestMapping(value = "/{category}" , method = RequestMethod.GET)
    @ResponseBody
    public Response getImagesByCategory(@PathVariable("category") String category){
        return new Response(ResponseStatus.SUCCESS , "请求成功" , imageService.getImages(category)) ;
    }

}
