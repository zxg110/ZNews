package com.for_futrue.zxg.znews.Model;

import com.for_futrue.zxg.znews.bean.Channel;

import java.util.List;

/**
 * Created by zxg on 2017/1/4.
 */
public interface ChannelModel {
    List<Channel> getChannelByFlag(int flag);

    void deleteAllChannel();

    void updateUserChannel(List<Channel> userChannelList);

    void updateOtherChannel(List<Channel> otherChannelList);
}
