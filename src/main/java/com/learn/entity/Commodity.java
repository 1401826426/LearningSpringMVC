package com.learn.entity;

import lombok.Data;

import java.sql.Blob;
/**
 * Created by pengfei on 2017/3/12.
 */
@Data
public class Commodity {
    private String id ;
    private String description ;
    private double price ;
    private byte[] image ;
    private String type ;
}














