package com.learn.web;

import com.learn.service.ImagePlotService;
import com.learn.util.Response;
import com.learn.util.ResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List ;

/**
 * Created by pengfei on 2017/4/26.
 */
@Controller
@RequestMapping("/plot")
//@SessionAttributes(value={"img1","img2","img3","img4","img5"} ,types={byte[].class})
public class ImagePlotController {

    private Logger log = LoggerFactory.getLogger(ImagePlotController.class) ;

    @Autowired
    private ImagePlotService imagePlotService;

    @RequestMapping(value = "/feature", method = RequestMethod.POST)
    @ResponseBody
    public Response plotFeature(@RequestParam("image") MultipartFile image,  HttpSession session) {
        List<byte[]> imgs  ;
        try {
            imgs = imagePlotService.printFeature(image);
        } catch (IOException e) {
            log.error("畫特征點出錯"  , e);
            return new Response(ResponseStatus.FAILURE , "內部出錯") ;
        }
        if(imgs == null || imgs.size() < 2){
            return new Response(ResponseStatus.FAILURE , "内部出错") ;
        }
        session.setAttribute("img1", imgs.get(0));
        session.setAttribute("img2", imgs.get(1));
        session.setAttribute("refresh" , image.hashCode());
        return new Response(ResponseStatus.SUCCESS, "畫圖成功");
    }


    @RequestMapping(value = "/match" , method = RequestMethod.POST)
    @ResponseBody
    public Response plotMatch(@RequestParam("image1") MultipartFile image1 ,
                              @RequestParam("image2") MultipartFile image2 ,
                              HttpSession session) {
        List<byte[]> images ;
        try {
            images = imagePlotService.printMatch(image1 , image2);
        } catch (IOException e) {
            log.error(session.getId() + "画匹配图出错");
            return new Response(ResponseStatus.FAILURE , "画图出错") ;
        }
        if(images == null || images.size() < 3){
            return new Response(ResponseStatus.FAILURE , "畫圖失敗") ;
        }
        session.setAttribute("img3" , images.get(0)) ;
        session.setAttribute("img4" , images.get(1)) ;
        session.setAttribute("img5" , images.get(2))  ;
        return new Response(ResponseStatus.SUCCESS , "畫圖成功") ;
    }


    @RequestMapping(value = "/image/{img}/{refresh}" , method = RequestMethod.GET )
    public void getImage(@PathVariable("img") String img ,
                         @PathVariable("refresh") String reresh ,
                         HttpServletResponse response , HttpSession session) {
        log.info(session.getId() + "获取图像" + reresh);
        Object obj = session.getAttribute(img) ;
        response.setContentType("image/png");
        if(obj != null && obj instanceof byte[]){
            byte[] image = (byte[])obj ;
            try {
                OutputStream os = response.getOutputStream() ;
                os.write(image , 0 , image.length);
            } catch (IOException e) {
                log.error("寫圖像流出錯" , e);
            }
        }else{
            log.error("沒有獲取對應的圖像");
        }
    }


}


























