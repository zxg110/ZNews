package com.for_futrue.zxg.znews.Model;

import android.content.Context;

import com.for_futrue.zxg.znews.bean.Channel;
import com.for_futrue.zxg.znews.database.ChannelDao;

import java.util.List;

/**
 * Created by zxg on 2017/1/4.
 */
public class ChannelModelImpl implements ChannelModel{

    private Context mContext;
    private ChannelDao mChannelDao;
    @Override
    public List<Channel> getChannelByFlag(int flag) {
        return mChannelDao.queryChannelBySelected(flag);
    }

    public ChannelModelImpl(Context context){
        this.mContext = context;
        mChannelDao = new ChannelDao(mContext);
    }
}
