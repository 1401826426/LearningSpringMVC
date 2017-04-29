package com.learn.exceptions;

/**
 * Created by pengfei on 2017/4/23.
 */
public class PageOutException extends Exception {
    public PageOutException(){
        super("页数超出边界");
    }

    public PageOutException(String message) {
        super(message);
    }

    public PageOutException(String message, Throwable cause) {
        super(message, cause);
    }

    public PageOutException(Throwable cause) {
        super(cause);
    }

}
