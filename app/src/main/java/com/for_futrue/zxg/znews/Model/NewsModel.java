package com.for_futrue.zxg.znews.Model;

import android.os.Message;

/**
 * Created by zxg on 2016/12/25.
 */
public interface NewsModel {

    public void loadNewsListByChannel(final int channel,final Message msg );

    public void loadNewsDetailByUrl(String docId,Message msg);
}
