package com.learn.service.impl;


import com.learn.service.CrawService;
import org.junit.Test;

/**
 * Created by pengfei on 2017/4/25.
 */
public class CrawServiceImplTest {

    @Test
    public void testCraw(){
        CrawService cs = new CrawServiceImpl() ;
        String url = "https://list.tmall.com/search_product.htm?spm=875.7931836/B.subpannel2016025.19.HxujXB&q=%D0%A1%CE%F7%D7%B0&pos=8&cat=50025135&style=g&from=sn_1_cat-qp&acm=2016030115.1003.2.708397&sort=s&tmhkmain=0&scm=1003.2.2016030115.OTHER_1493004471202_708397#J_crumbs" ;
        String type = "suit" ;
        cs.craw(url , type)  ;
    }

}