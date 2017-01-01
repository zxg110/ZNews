package com.for_futrue.zxg.znews.presenter;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;

import com.for_futrue.zxg.znews.bean.Channel;
import com.for_futrue.zxg.znews.database.ChannelDao;
import com.for_futrue.zxg.znews.view.MainNewsView;
import com.for_futrue.zxg.znews.view.Ui;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zxg on 2016/3/21.
 */
public class MainPresenter extends Presenter<MainNewsView>{
    private final static String TAG = MainPresenter.class.getSimpleName();
    private Context mContext;
    public static final int CHANNEL_RECOMMEND = 0;
    public static final int CHANNEL_HEADLINE = 1;
    public static final int CHANNEL_AMUSEMENT = 2;
    public static final int CHANNEL_SPORTS = 3;
    public static final int CHANNEL_ECONOMICS = 4;
    public static final int CHANNEL_MILITARY = 6;
    public static final int CHANNEL_PHONE = 7;
    public static final int CHANNEL_TECHNOLOGY = 8;
    public static final int CHANNEL_GAME = 9;
    public static final int CHANNEL_DITIGAL = 10;
    public static final int CHANNEL_EDUCATION = 11;
    public static final int CHANNEL_JOKE = 12;
    public static final int CHANNEL_CAR = 13;
    public static final int CHANNEL_NBA= 14;
    public static final int CHANNEL_TRAVEL = 16;
    public static final int CHANNEL_MOM = 17;
    public MainPresenter(Context context){
        mContext = context;
    }
    public List<Channel> getUserChannel(){
        ChannelDao channelDao= new ChannelDao(mContext);
        List<Channel> channelList = new ArrayList<Channel>();
        channelList = channelDao.queryChannelBySelected();
        Log.i(TAG,"channelListSize:"+channelList.size());
        return channelList;
    }

    public int getWindowsWidth(Activity activity){
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }


}
