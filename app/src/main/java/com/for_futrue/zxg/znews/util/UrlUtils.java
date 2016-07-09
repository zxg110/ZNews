package com.for_futrue.zxg.znews.util;

import android.content.Context;
import android.util.Log;

/**
 * Created by zxg on 2016/4/6.
 */
public class UrlUtils {
    public static final int PAGE_SIZE = 20;

    public static final String PROJECT_NAME = "/news";

    private static final String NEWS_LIST_ACTION = "/loadnews.do";

    public static final String NEWS_DETAIL_ACTION="/newsdetail.do";

    public static String getNewsListAddress(String channel){

        return ConfigUtils.getConfigIP()+ConfigUtils.getConfigPort()+NEWS_LIST_ACTION+"?"+channel;
    }
    public static String getPort(){
        return ConfigUtils.getConfigPort();
    }
}
