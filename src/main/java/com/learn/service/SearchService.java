package com.learn.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List ;
/**
 * Created by pengfei on 2017/4/28.
 */
public interface SearchService {
    List<String> siftSearch(MultipartFile image) throws IOException;
}
