package com.learn.service.impl;

import com.learn.components.ImageResourceManager;
import com.learn.components.SiftCmdGenerator;
import com.learn.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by pengfei on 2017/4/28.
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired private ImageResourceManager imageResourceManager ;

    @Autowired private SiftCmdGenerator siftCmdGenerator  ;

    public List<String> siftSearch(MultipartFile image) throws IOException {
        File imageFile = imageResourceManager.writeImage(image.getBytes() , image.getOriginalFilename()) ;
        List<String> result = siftCmdGenerator.search(imageFile) ;
        imageResourceManager.deleteImage(imageFile.getName()) ;
        return result;
    }

}

























