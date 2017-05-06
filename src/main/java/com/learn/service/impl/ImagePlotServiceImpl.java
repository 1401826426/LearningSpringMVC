package com.learn.service.impl;

import com.learn.components.SiftCmdGenerator;
import com.learn.components.SocketSender;
import com.learn.service.ImagePlotService;
import com.learn.components.ImageResourceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by pengfei on 2017/4/26.
 */
@Service
public class ImagePlotServiceImpl implements ImagePlotService {


    @Autowired  private SocketSender socketSender ;
    @Autowired  private ImageResourceManager imageResourceManager ;
    @Autowired  private SiftCmdGenerator siftCmdGenerator ;
    private final static int MAX_TRY = 5 ;

    @Deprecated
    public List<byte[]> printFeature(MultipartFile image) throws IOException {
        String name = UUID.randomUUID().toString() +  image.getOriginalFilename() ;
        String outName = UUID.randomUUID().toString() + image.getOriginalFilename() ;
        File img = imageResourceManager.writeImage(image.getBytes() , name) ;
        String inputPath = img.getAbsolutePath() ;
        String outputPath = img.getParentFile().getAbsolutePath() + File.separator + outName ;
        String cmd = "1 " + inputPath + " " + outputPath ;
        for(int i = 0;i < MAX_TRY;i++) {
            String result = socketSender.send("localhost", 10001, cmd);
            if("success".equals(result)){
                List<byte[]> bytes = new ArrayList<byte[]>() ;
                byte[] img1 = imageResourceManager.getBytes(name) ;
                imageResourceManager.deleteImage(name) ;
                byte[] img2 = imageResourceManager.getBytes(outName) ;
                imageResourceManager.deleteImage(outName) ;
                bytes.add(img1) ;
                bytes.add(img2) ;
                return bytes ;
            }
        }
        return null;
    }

    @Deprecated
    public List<byte[]> printMatch(MultipartFile img1, MultipartFile img2) throws IOException {
        String name1 = UUID.randomUUID() + img1.getOriginalFilename() ;
        String name2 = UUID.randomUUID() + img2.getOriginalFilename() ;
        String outName = UUID.randomUUID() + ".jpg" ;
        File image1 = imageResourceManager.writeImage(img1.getBytes() , name1) ;
        File image2 = imageResourceManager.writeImage(img2.getBytes() , name2) ;
        String input1 = image1.getAbsolutePath() ;
        String input2 = image2.getAbsolutePath() ;
        String output = image1.getParentFile().getAbsolutePath() + File.separator + outName ;
        String cmd = "3 " + input1 + " " + input2 + " " + output ;
        for(int i = 0;i < MAX_TRY;i++){
            String result = socketSender.send("localhost" , 10001, cmd) ;
            if("success".equals(result)){
                byte[] result1 = imageResourceManager.getBytes(name1) ;
                byte[] result2 = imageResourceManager.getBytes(name2) ;
                byte[] result3 = imageResourceManager.getBytes(outName) ;
                List<byte[]> results = new ArrayList<byte[]>() ;
                results.add(result1) ; imageResourceManager.deleteImage(name1) ;
                results.add(result2) ; imageResourceManager.deleteImage(name2) ;
                results.add(result3) ; imageResourceManager.deleteImage(outName) ;
                return results ;
            }
        }
        return null;
    }

    public List<byte[]> printMatchV2(MultipartFile img1, MultipartFile img2) throws IOException {
        String name1 = UUID.randomUUID() + img1.getOriginalFilename() ;
        String name2 = UUID.randomUUID() + img2.getOriginalFilename() ;
        File image1 = imageResourceManager.writeImage(img1.getBytes() , name1) ;
        File image2 = imageResourceManager.writeImage(img2.getBytes() , name2) ;
        File match = siftCmdGenerator.match(image1 , image2) ;
        if(match == null)return null ;
        List<byte[]> bytes = new ArrayList<byte[]>() ;
        bytes.add(imageResourceManager.getBytes(image1.getName())) ;
        bytes.add(imageResourceManager.getBytes(image2.getName())) ;
        bytes.add(imageResourceManager.getBytes(match.getName())) ;
        return bytes ;
    }

    public List<byte[]> printFeatureV2(MultipartFile image) throws IOException {
        String name = UUID.randomUUID().toString() +  image.getOriginalFilename() ;
        File img = imageResourceManager.writeImage(image.getBytes() , name) ;
        File feature = siftCmdGenerator.plotFeature(img) ;
        if(feature == null)
            return null ;
        List<byte[]> result = new ArrayList<byte[]>() ;
        byte[] imgBytes = imageResourceManager.getBytes(img.getName()) ;
        byte[] featureBytes = imageResourceManager.getBytes(feature.getName()) ;
        result.add(imgBytes) ;
        result.add(featureBytes) ;
        return result ;
    }

}














