package com.learn.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List ;
/**
 * Created by pengfei on 2017/4/26.
 */
public interface ImagePlotService {


    @Deprecated
    List<byte[]> printFeature(MultipartFile image) throws IOException;

    @Deprecated
    List<byte[]> printMatch(MultipartFile img1 , MultipartFile img2) throws IOException;

    List<byte[]> printFeatureV2(MultipartFile image) throws IOException;

    List<byte[]> printMatchV2(MultipartFile img1 , MultipartFile img2) throws IOException;

    List<byte[]> printColorHist(MultipartFile image) throws IOException;;
}
