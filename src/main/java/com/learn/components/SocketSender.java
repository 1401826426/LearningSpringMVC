package com.learn.components;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by pengfei on 2017/4/24.
 */
@Component
public class SocketSender {

//    private Executor executor = Executors.
    ThreadPoolExecutor executors = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);


    public String send(String url , int port , String words , int timeout){
        return null ;
    }


    public String send(String url ,int port ,  String words) throws IOException {
        StringBuilder sb = new StringBuilder("") ;
        Socket socket = new Socket(url , port) ;
        OutputStream os = socket.getOutputStream() ;
        os.write((words+"\n").getBytes());
        os.write("eof\n".getBytes());
        InputStream is = socket.getInputStream() ;
        Scanner sc = new Scanner(is) ;
        boolean flag = false;
        while(sc.hasNext()){
            String s = sc.nextLine() ;
            if("eof".equals(s))break ;
            if(flag)sb.append("\n") ;
            sb.append(s) ;
            flag =  true ;
        }
        is.close();
        os.close();
        socket.close();
        return sb.toString() ;
    }


//    private class Task implements Callable<String>{
//
//        private Socket socket ;
//        private String cmd ;
//        public Task(Socket socket , String cmd){
//            this.socket = socket ;
//            this.cmd = cmd ;
//        }
//
//
//        private void writeValue(InputStream)
//
//        public String call() throws Exception {
//
//            return null;
//        }
//
//
//    }


//    public String sendV2(String url , int port , String words) throws IOException {
//        StringBuilder sb = new StringBuilder("") ;
//        Socket socket = new Socket(url , port) ;
//        OutputStream os = socket.getOutputStream() ;
//        PrintWriter pw = new PrintWriter(os) ;
//        pw.println(words);
//        pw.flush();
//        pw.println("eof");
//        pw.flush();
//        InputStream is = socket.getInputStream() ;
//        Scanner sc = new Scanner(is) ;
//        boolean flag = false;
//        while(sc.hasNext()){
//            String s = sc.nextLine() ;
//            if("eof".equals(s))break ;
//            if(flag)sb.append("\n") ;
//            sb.append(s) ;
//        }
//        is.close();
//        os.close();
//        socket.close();
//        return sb.toString() ;
//    }
}





