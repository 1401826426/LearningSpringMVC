package com.learn.dto;

import com.learn.exceptions.PageOutException;
import lombok.Data;

import java.util.List ;
/**
 * Created by pengfei on 2017/4/23.
 */
@Data
public class DataWithPage<T> {
    private final static int DEFAULT_PAGE_NUM = 8 ;
    private List<T> data ;
    private int curPage ;
    private int totalPage ;
    private int totalNum ;
    private int pageNum = DEFAULT_PAGE_NUM ;
    public DataWithPage(int curPage , int totalNum){
        this.curPage = curPage  ;
        this.totalNum = totalNum ;
        this.totalPage = totalNum/pageNum;
    }
    public DataWithPage(int curPage , int totalNum , int pageNum){
        this.pageNum = pageNum ;
        this.totalNum = totalNum ;
        this.curPage = curPage ;
        this.totalPage = this.totalNum/pageNum ;
    }
    public int getPageOffset() throws PageOutException {
        if(curPage < 0 || curPage > totalPage){
            throw  new PageOutException() ;
        }
        return (curPage)*pageNum ;
    }
    public List<T> getData() {
        return data;
    }
    public void setData(List<T> data) {
        this.data = data;
    }
    public int getCurPage() {
        return curPage;
    }
    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }
    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
}
