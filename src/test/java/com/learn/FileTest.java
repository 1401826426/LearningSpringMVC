package com.learn;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.*;

/**
 * Created by pengfei on 2017/4/2.
 */

public class FileTest {

    @Test
    public void testFiles() throws IOException {
        InputStream is = new FileInputStream(new File("H:\\求职资料\\照片\\upload.jpg")) ;
        OutputStream os = new FileOutputStream(new File("E:\\test")) ;
        IOUtils.copy(is , os) ;
    }

}
