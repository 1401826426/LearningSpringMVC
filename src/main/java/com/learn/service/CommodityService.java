package com.learn.service;

import com.learn.entity.Commodity;

import java.util.List ;

/**
 * Created by pengfei on 2017/4/9.
 */
public interface CommodityService {

    List<Commodity> getImages(String category) ;

}
