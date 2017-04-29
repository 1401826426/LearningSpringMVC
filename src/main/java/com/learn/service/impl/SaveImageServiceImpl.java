package com.learn.service.impl;

import com.learn.dao.CommodityDao;
import com.learn.entity.Commodity;
import com.learn.entity.Image;
import com.learn.service.SaveImageSerive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List ;
/**
 * Created by pengfei on 2017/4/28.
 */
@Service
public class SaveImageServiceImpl implements SaveImageSerive{

    @Autowired private CommodityDao commodityDao;
    public void save(int num, String pos) throws IOException {
        File file = new File(pos) ;
        if(!file.exists())file.mkdirs() ;
        List<String> types = commodityDao.getAllType() ;
        for(String type : types){
            List<Commodity> commodities = commodityDao.queryByCategoryWithPage(type , 0 , num) ;
            for(Commodity commodity:commodities){
                File image = new File(file , commodity.getId() + ".jpg") ;
                Image img = commodityDao.queryImage(commodity.getId()) ;
                OutputStream os = new FileOutputStream(image) ;
                os.write(img.getImage());
                os.close();
            }
        }
    }
}
