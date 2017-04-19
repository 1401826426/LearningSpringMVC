package com.learn.util;

import java.io.Serializable;

/**
 * Created by pengfei on 2017/3/11.
 */
public class Response<T> implements Serializable {

    private ResponseStatus status ;

    private String message ;

    private T data ;

    public Response(ResponseStatus status , String message){
        this.status = status ;
        this.message = message ;
    }

    public Response(ResponseStatus status  , String message , T data){
        this.status =  status ;
        this.message = message ;
        this.data = data ;
    }

}
