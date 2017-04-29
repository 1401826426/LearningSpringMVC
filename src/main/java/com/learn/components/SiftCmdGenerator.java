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

    private final static String PLOT_MATCH = "3" ;

    private final static String SEARCH = "4 F:\\images" ;

    private final static String url = "localhost" ;

    private final static int port = 10001 ;

    @Autowired  private SocketSender socketSender ;

    public List<byte[]> printFeature(String image){
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

    public List<String> search(File image) throws IOException {
        String imagePath = image.getAbsolutePath() ;
        String cmd = SEARCH + " " + imagePath ;
        String result = socketSender.send(url , port , cmd) ;
        if(result == null || "".equals(result))return null ;
        String[] ss = result.split("\n") ;
        if("fail".equals(ss[0]))return null ;
        List<String> list = new ArrayList<String>() ;
        for(int i = 1;i < ss.length;i++){
            int index = ss[i].indexOf(".") ;
            list.add(ss[i].substring(0 , index)) ;
        }
        return list ;
    }

}



















