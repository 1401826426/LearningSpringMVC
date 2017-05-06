package com.learn.dao;

import com.learn.entity.Commodity;
import com.learn.entity.Image;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by pengfei on 2017/4/9.
 */
@SuppressWarnings("unused")
public interface CommodityDao {

    List<Commodity> queryByCategory(@Param("category") String category) ;

    Image queryImage(@Param("id") String id) ;

    List<Commodity> queryByCategoryWithPage(@Param("type") String type, @Param("offset") int offset,@Param("limit") int limit);

    List<Commodity> queryAll(@Param("offset") int offset,@Param("limit") int limit);

    Integer queryNum(@Param("type")String type) ;

    Integer queryAllNum() ;

    List<String> getAllType();

    void delete(@Param("id") String id);
}
