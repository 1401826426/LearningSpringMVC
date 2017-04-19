package com.learn.dao;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


/**
 * Created by pengfei on 2017/3/11.
 */
public class FileSystemBaseTest {

    private FileSystem fs ;

    @Before
    public void init() throws IOException, URISyntaxException {
        Configuration conf = new Configuration() ;
        this.fs = FileSystem.get(new URI("hdfs://192.168.31.255:8020") , conf) ;
    }

    @Test
    public void testMkdir() throws Exception{
        fs.mkdirs(new Path("/maxTemperature")) ;
    }

}