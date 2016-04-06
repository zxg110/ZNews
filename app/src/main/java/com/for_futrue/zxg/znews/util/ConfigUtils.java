package com.for_futrue.zxg.znews.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by zxg on 2016/4/6.
 */
public class ConfigUtils {

    private static Properties properties;

    static {
        properties = new Properties();
    }
    private ConfigUtils(){
    }
    private static Properties getNetConfigProperties(){
        InputStream in = ConfigUtils.class.getResourceAsStream("/netconfig.properties");
        try{
            properties.load(in);
        }catch(IOException e){
            e.printStackTrace();
        }
        return properties;
    }
    /***************对外开放接口*******************/
    public static String getConfigIP(){
        return getNetConfigProperties().getProperty("IP");
    }

    public static String getConfigPort(){
        return getNetConfigProperties().getProperty("PORT");
    }


}
