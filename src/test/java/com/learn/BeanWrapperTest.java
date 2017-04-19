package com.learn;

import lombok.Data;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

/**
 * Created by pengfei on 2016/10/22.
 */
@Data
class User{
    String username ;
}

public class BeanWrapperTest {




    @Test
    public void testBeanWrapper(){
        User user = new User() ;
        BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(user) ;
        bw.setPropertyValue("username" , "张三");
        System.out.println(user.getUsername()) ;
    }

}





















































