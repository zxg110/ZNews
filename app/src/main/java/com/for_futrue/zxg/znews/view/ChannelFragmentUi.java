package com.for_futrue.zxg.znews.view;

import com.for_futrue.zxg.znews.bean.Channel;

import java.util.List;

/**
 * Created by zxg on 2017/1/3.
 */
public interface ChannelFragmentUi extends Ui{
    List<Channel> getUserChannelList();

    List<Channel> getOtherChannelList();
}
