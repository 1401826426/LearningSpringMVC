package com.learn.entity;
/**
 * Created by pengfei on 2017/4/22.
 */
//@Data
public class Image {

    private String id ;

    private byte[] image ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

}
