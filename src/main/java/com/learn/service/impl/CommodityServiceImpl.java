package com.learn.service.impl;

import com.learn.dao.CommodityDao;
import com.learn.entity.Commodity;

import java.util.List ;

import com.learn.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by pengfei on 2017/4/9.
 */
@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired private CommodityDao imageDao ;

    public List<Commodity> getImages(String category) {
        return imageDao.queryByCategory(category);
    }
}
