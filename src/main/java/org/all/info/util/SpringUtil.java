package org.all.info.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtil {

    private static ApplicationContext ac;

    private static void buildApplicationContext(){
        ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    public static ApplicationContext getApplicationContext(){
        if (ac == null){
            buildApplicationContext();
        }
        return ac;
    }
}
