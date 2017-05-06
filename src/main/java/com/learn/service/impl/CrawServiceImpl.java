package com.learn.service.impl;

import com.learn.components.SocketSender;
import com.learn.service.CrawService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by pengfei on 2017/4/24.
 */
@Service
public class CrawServiceImpl implements CrawService{
    @Autowired private SocketSender socketSender ;
    private static final Logger log = LoggerFactory.getLogger(CrawServiceImpl.class) ;
    private static final String CONN_URL = "localhost" ;
    private static final int port = 10000 ;
    private static final int DEFAULT_CRAW_TIMEOUT = 600 ;
    public String craw(String url, String type) {
        int index = url.indexOf("?") ;
        String pureUrl = url ;
        if(index != -1) {
            pureUrl = url.substring(0, index);
            String paramUrl = url.substring(index + 1);
            String[] params = paramUrl.split("&");
            boolean flag = false;
            for (String param : params) {
                if (param.indexOf("/") == -1) {
                    if (flag) pureUrl += "&";
                    else pureUrl += "?";
                    pureUrl += param;
                    flag = true;

                }
            }
        }
        String cmd = pureUrl + "\t" + type ;
        return  socketSender.send(CONN_URL , port , cmd , DEFAULT_CRAW_TIMEOUT);
    }

}










