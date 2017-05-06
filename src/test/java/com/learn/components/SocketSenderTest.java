package com.learn.components;


import org.junit.Test;

import java.io.IOException;

/**
 * Created by pengfei on 2017/4/26.
 */
public class SocketSenderTest {

    @Test
    public void testSend() throws IOException {
        String url = "localhost" ;
        int port = 10001 ;
        String cmd = "1 E:\\test\\5.jpg E:\\test\\6.jpg" ;
        SocketSender ss = new SocketSender() ;
        System.out.println(ss.send(url , port  ,cmd)) ;
    }

}