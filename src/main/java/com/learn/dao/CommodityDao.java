package com.learn.dao;

import com.learn.entity.Commodity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by pengfei on 2017/4/9.
 */
@SuppressWarnings("unused")
public interface CommodityDao {

    List<Commodity> queryByCategory(@Param("category") String category) ;

}
