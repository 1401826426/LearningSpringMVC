package com.learn.components;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List ;
import java.util.UUID;

/**
 * Created by pengfei on 2017/4/28.
 */
@Component
public class SiftCmdGenerator {

    private final static String SAVE_FEATURE = "2";

    private final static String PLOT_FEATURE = "1" ;

    private final static int DEFAULT_PLOT_FEATURE_TIMEOUT = 60 ;

    private final static String PLOT_MATCH = "3" ;

    private final static int DEFAULT_PLOT_MATCH_TIMEOUT = 60 ;

    private final static String SEARCH = "4 F:\\images" ;

    private final static int DEFAULT_SEARCH_TIMEOUT = 1000 ;

    private final static String HIST = "5" ;

    private final static int DEFAULT_PLOT_HIST_TIMEOUT = 50 ;


    private final static String url = "localhost" ;

    private final static int port = 10001 ;

    private final static String SUCCESS = "success" ;

    @Autowired  private SocketSender socketSender ;

    public File plotFeature(File image){
        String imagePath = image.getAbsolutePath() ;
        String featurePath = image.getParentFile().getAbsolutePath()
                + File.separator + UUID.randomUUID() + ".jpg" ;
        String cmd = PLOT_FEATURE + " " + imagePath + " " + featurePath ;
        String result = socketSender.send(url , port , cmd , DEFAULT_PLOT_FEATURE_TIMEOUT) ;
        if(SUCCESS.equals(result))
            return new File(featurePath) ;
        return null ;
    }

    public File  saveFeature(File image) throws IOException {
        String imagePath = image.getAbsolutePath() ;
        String featurePath = image.getParentFile().getAbsolutePath() +
                File.separator + UUID.randomUUID().toString() + ".txt" ;
        String cmd = SAVE_FEATURE + " " + imagePath + " " + featurePath ;
        socketSender.send(url , port , cmd) ;
        return new File(featurePath) ;
    }

    /**
     * 检索image对象中的所有数据
     * @param image
     * @return
     * @throws IOException
     */
    public List<String> search(File image) throws IOException {
        String imagePath = image.getAbsolutePath() ;
        String cmd = SEARCH + " " + imagePath ;
        String result = socketSender.send(url , port , cmd , DEFAULT_SEARCH_TIMEOUT) ;
        if(result == null || "".equals(result))return null ;
        String[] ss = result.split("\n") ;
        if(!SUCCESS.equals(ss[0]))return null ;
        List<String> list = new ArrayList<String>() ;
        for(int i = 1;i < ss.length;i++){
            int index = ss[i].indexOf(".") ;
            list.add(ss[i].substring(0 , index)) ;
        }
        return list ;
    }

    /**
     * 发出绘制匹配图请求,最终返回匹配图的文件对象
     * 如果中间出错，则返回null
     * @param img1
     * @param img2
     * @return
     */
    public File match(File img1 , File img2){
        String imgName1 = img1.getName() ;
        String imgName2 = img2.getName() ;
        String match_name = img1.getParentFile().getAbsolutePath() + File.separator
                + imgName1.substring(0,imgName1.lastIndexOf("."))
                + "_" + imgName2.substring(0 , imgName2.lastIndexOf("."))
                + ".jpg" ;
        String cmd = PLOT_MATCH + " " + img1.getAbsolutePath() + " " + img2.getAbsolutePath() +
                " " + match_name ;
        String result = socketSender.send(url , port , cmd , DEFAULT_PLOT_MATCH_TIMEOUT) ;
        if(result == null || !SUCCESS.equals(result)){
            return null ;
        }
        return new File(match_name) ;
    }

    public File plotHist(File img) {
        String imagePath = img.getAbsolutePath() ;
        String histPath = img.getParentFile().getAbsolutePath()
                + File.separator + UUID.randomUUID() + ".jpg" ;
        String cmd = HIST + " " + imagePath + " " + histPath ;
        String result = socketSender.send(url , port , cmd , 20) ;
        if(SUCCESS.equals(result))
            return new File(histPath) ;
        return null ;
    }
}



















