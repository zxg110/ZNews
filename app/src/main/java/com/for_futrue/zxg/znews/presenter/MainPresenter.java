package com.for_futrue.zxg.znews.presenter;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

import com.for_futrue.zxg.znews.bean.Channel;
import com.for_futrue.zxg.znews.view.MainNewsView;
import com.for_futrue.zxg.znews.view.Ui;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zxg on 2016/3/21.
 */
public class MainPresenter extends Presenter<MainNewsView>{
    private Context mContext;
    public MainPresenter(Context context){
        mContext = context;
    }
    public List<Channel> getUserChannel(){
        List<Channel> channelList = new ArrayList<Channel>();
        Channel c1 = new Channel(1,"头条",1,1,"头条");
        Channel c2 = new Channel(1,"娱乐",2,1,"娱乐");
        Channel c3 = new Channel(1,"体育",1,3,"体育");
        Channel c4 = new Channel(1,"科技",1,4,"科技");
        Channel c5 = new Channel(1,"互联网",1,5,"互联网");
        Channel c6 = new Channel(1,"轻松一刻",1,6,"轻松一刻");
        Channel c7 = new Channel(1,"历史",1,7,"历史");
        channelList.add(c1);
        channelList.add(c2);
        channelList.add(c3);
        channelList.add(c4);
        channelList.add(c5);
        channelList.add(c6);
        channelList.add(c7);
        return channelList;
    }

    public int getWindowsWidth(Activity activity){
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

}