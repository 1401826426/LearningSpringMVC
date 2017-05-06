package com.learn.components;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.*;

/**
 * Created by pengfei on 2017/4/24.
 */
@Component
public class SocketSender {

    private ThreadPoolExecutor executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(10);

    private static final String EOF = "eof" ;

    public String send(String url , int port , String words , int timeout){
        try {
            Socket socket = new Socket(url , port) ;
            Future<String> future = executor.submit(this.new Task(socket , words)) ;
            String result = future.get(timeout , TimeUnit.SECONDS) ;
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null ;
    }

    private class Task implements Callable<String>{

        private Socket socket ;
        private String cmd ;
        public Task(Socket socket , String cmd){
            this.socket = socket ;
            this.cmd = cmd ;
        }

        private void writeValue() throws IOException {
            OutputStream os = socket.getOutputStream() ;
            os.write((cmd + "\n").getBytes());
            os.write((EOF + "\n").getBytes());
        }

        private String readValue() throws IOException {
            InputStream is = socket.getInputStream() ;
            Scanner in = new Scanner(is) ;
            StringBuilder sb = new StringBuilder("") ;
            while(in.hasNext()){
                String s = in.nextLine() ;
                if(EOF.equals(s))
                    break ;
                sb.append(s + "\n") ;
            }
            return sb.toString() ;
        }

        public String call() throws Exception {
            writeValue();
            String result = readValue() ;
            socket.close();
            return result;
        }


    }


    @Deprecated
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

}





