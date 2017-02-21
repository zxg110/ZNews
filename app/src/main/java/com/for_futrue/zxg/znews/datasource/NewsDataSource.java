package com.for_futrue.zxg.znews.datasource;


import android.os.Message;

import com.for_futrue.zxg.znews.bean.News;

import java.util.List;

/**
 * Created by zxg on 2016/4/5.
 */
public interface NewsDataSource {
    void loadNewsListByChannel(int channelDesc,int index,Message msg);
    void getNewsContentByUrl(String docId,Message msg);
}
