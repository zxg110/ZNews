package com.for_futrue.zxg.znews.datasource;

import android.os.Message;

import com.for_futrue.zxg.znews.bean.News;

import java.util.List;

/**
 * Created by zxg on 2016/4/5.
 */
public class NetDataSource implements NewsDataSource{
    @Override
    public void getNewsListByChannel(String channel,Message msg) {

    }

    @Override
    public String getNewsContentByUrl(String url) {
        return null;
    }
}
