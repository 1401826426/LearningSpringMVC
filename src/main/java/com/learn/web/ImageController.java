package com.learn.web;

import com.learn.dto.DataWithPage;
import com.learn.entity.Image;
import com.learn.exceptions.PageOutException;
import com.learn.service.CommodityService;
import com.learn.util.Response;
import com.learn.util.ResponseStatus;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by pengfei on 2017/3/11.
 */
@Controller
@RequestMapping("/image")
public class ImageController {

    private @Autowired
    CommodityService imageService ;
    private Log log = LogFactory.getLog(this.getClass()) ;

    /**
     * @api {post} /image/upload
     * @apiParam {File} image 要传入的文件
     * @apiParam {String} category  类别
     * @apiGroup image
     * @param image
     * @param category
     * @return
     */
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

    /** @api {get} /image/:category 查询当前类别下的所有图像
     * @apiParam {String} category类别
     * @apiGroup image
     * @param category
     * @return
     */
    @RequestMapping(value = "/{category}" , method = RequestMethod.GET)
    @ResponseBody
    public Response getImagesByCategory(@PathVariable("category") String category){
        return new Response(ResponseStatus.SUCCESS , "请求成功" , imageService.getImages(category)) ;
    }

    /**
     * @api {get} /image/id/:id  获取对应id的图片
     * @apiParam {String} id
     * @apiGroup image
     * @param response
     * @param id
     * @throws IOException
     */
    @RequestMapping(value = "/id/{id}" , method = RequestMethod.GET)
    public void getImage(HttpServletResponse response , @PathVariable("id") String id) throws IOException {
        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
        Image image = imageService.getImage(id);
        if(image == null) return  ;
        byte[] bytes = image.getImage() ;
        os.write(bytes , 0,bytes.length);
    }

    /**
     * @api {delete} /image/id/:id 删除对应id的图片
     * @apiGroup image
     * @param id
     * @return
     */
    @RequestMapping(value = "/id/{id}" , method = RequestMethod.DELETE)
    @ResponseBody
    public Response deleteImage(@PathVariable("id") String id){
        imageService.deleteImage(id) ;
        return new Response(ResponseStatus.SUCCESS , "删除成功") ;
    }

    /**
     * @api {get} /image/type/:type/page/:page  获取对应类型的第几页商品
     * @apiParam {String} type  类型
     * @apiParam {Number} page 页数
     * @apiGroup image
     * @param type
     * @param page
     * @return
     */
    @RequestMapping(value = "/type/{type}/page/{page}" , method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Response getCommodityByCategory(@PathVariable("type") String type , @PathVariable("page") Integer page){
        try{
            DataWithPage dwp = imageService.getCommodities(type , page);
            return new Response(ResponseStatus.SUCCESS , "请求成功" , dwp) ;
        } catch (PageOutException e) {
            log.info("超过page范围" , e);
            return new Response(ResponseStatus.FAILURE , "请求失败") ;
        }

    }

    /**
     * @api {get} /image/types  获取所有的商品类型
     * @apiGroup image
     * @return
     */
    @RequestMapping(value="/types" , method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Response getAllType(){
        return new Response(ResponseStatus.SUCCESS , "请求成功", imageService.getAllType()) ;
    }

}
