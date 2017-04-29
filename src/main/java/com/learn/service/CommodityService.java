package com.learn.service;

import com.learn.dto.DataWithPage;
import com.learn.entity.Commodity;
import com.learn.entity.Image;
import com.learn.exceptions.PageOutException;

import java.util.List ;

/**
 * Created by pengfei on 2017/4/9.
 */
public interface CommodityService {

    List<Commodity> getImages(String category) ;

    Image getImage(String id) ;

    DataWithPage getCommodities(String type , Integer page) throws PageOutException;

    List<String> getAllType();

    void deleteImage(String id);
}
