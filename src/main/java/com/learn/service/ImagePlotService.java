package com.learn.service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List ;
/**
 * Created by pengfei on 2017/4/26.
 */
public interface ImagePlotService {


    public List<byte[]> printFeature(MultipartFile image) throws IOException;

    public List<byte[]> printMatch(MultipartFile img1 , MultipartFile img2) throws IOException;


}
