package com.learn.components;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * Created by pengfei on 2017/4/26.
 */

/**
 * ImageResourceManager主要是对图像文件的处理，在画图时主要是用来处理绘制
 * 好的图片
 */
@Component
public class ImageResourceManager implements InitializingBean , DisposableBean{

    private static final String  TMP = "tmp/" ;

    private  File root ;

    public void afterPropertiesSet() throws Exception {
        root = new File(TMP) ;
        if(!root.exists())
            root.mkdirs() ;
    }

    public  File writeImage(byte[] bytes , String name) throws IOException {
        File file = new File(root , name) ;
        OutputStream os = new FileOutputStream(file) ;
        os.write(bytes) ;
        os.close();
        return  file ;
    }

    public  boolean  deleteImage(String name){
        File file = new File(root , name) ;
        return file.delete() ;
    }

    public  byte[] getBytes(String name) throws IOException {
        File file = new File(root , name) ;
        InputStream is = new FileInputStream(file) ;
        byte[] bytes = new byte[is.available()] ;
        is.read(bytes) ;
        is.close();
        return bytes ;
    }

    public void destroy() throws Exception {
        File file = new File(TMP) ;
        if(file.exists()) file.delete() ;
    }
}














