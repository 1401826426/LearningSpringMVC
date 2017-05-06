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

    /**
     * @api {post} /plot/feature 绘制特征点图
     * @apiParam {File} image  图像文件
     * @apiGroup plot
     * @param image
     * @param session
     * @return
     */
    @RequestMapping(value = "/feature", method = RequestMethod.POST)
    @ResponseBody
    public Response plotFeature(@RequestParam("image") MultipartFile image,  HttpSession session) {
        List<byte[]> imgs  ;
        try {
            imgs = imagePlotService.printFeatureV2(image);
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


    /**
     * @api {post} /plot/match 绘制匹配图片
     * @apiParam {File} image1 第一幅图像
     * @apiParam {File} image2 第二幅图像
     * @apiGroup plot
     * @param image1
     * @param image2
     * @param session
     * @return
     */
    @RequestMapping(value = "/match" , method = RequestMethod.POST)
    @ResponseBody
    public Response plotMatch(@RequestParam("image1") MultipartFile image1 ,
                              @RequestParam("image2") MultipartFile image2 ,
                              HttpSession session) {
        List<byte[]> images ;
        try {
            images = imagePlotService.printMatchV2(image1 , image2);
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


    /**
     * @api /plot/image/:img/:refresh  获取画出的图片
     * @apiParam {String} img 绘制图片的id
     * @apiParam {String} refresh 刷新的地址
     * @apiGroup plot
     * @param img
     * @param reresh
     * @param response
     * @param session
     */
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


























