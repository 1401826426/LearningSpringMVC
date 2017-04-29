package com.learn.service.impl;

import com.learn.dao.CommodityDao;
import com.learn.dto.DataWithPage;
import com.learn.entity.Commodity;

import java.util.List ;

import com.learn.entity.Image;
import com.learn.exceptions.PageOutException;
import com.learn.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by pengfei on 2017/4/9.
 */
@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired private CommodityDao imageDao ;

    private static final int   PAGE_NUM = 8;

    public List<Commodity> getImages(String category) {
        return imageDao.queryByCategory(category);
    }

    public Image getImage(String id) {
        return  imageDao.queryImage(id);
    }

    public DataWithPage  getCommodities(String type , Integer page) throws PageOutException {
//        DataWithPage<Commodity> result = DataWithPage<Commodity>()
        if(type == null || "all".equals(type)){
            int totalNum = imageDao.queryAllNum() ;
            DataWithPage dwp = new DataWithPage(page , totalNum , PAGE_NUM) ;
            List<Commodity> list = imageDao.queryAll(dwp.getPageOffset() , dwp.getPageNum()) ;
            dwp.setData(list);
            return  dwp;
        }else{
            int totalNum = imageDao.queryNum(type) ;
            DataWithPage dwp = new DataWithPage(page , totalNum , PAGE_NUM) ;
            List<Commodity> list = imageDao.queryByCategoryWithPage(type , dwp.getPageOffset() , dwp.getPageNum()) ;
            dwp.setData(list);
            return dwp ;
        }
    }

    public List<String> getAllType() {
        return imageDao.getAllType();
    }

    public void deleteImage(String id) {
        imageDao.delete(id) ;
    }

}







