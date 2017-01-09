package com.for_futrue.zxg.znews.presenter;

import android.content.Context;

import com.for_futrue.zxg.znews.Model.ChannelModel;
import com.for_futrue.zxg.znews.Model.ChannelModelImpl;
import com.for_futrue.zxg.znews.bean.Channel;
import com.for_futrue.zxg.znews.database.ChannelDao;
import com.for_futrue.zxg.znews.fragment.ChannelFragment;
import com.for_futrue.zxg.znews.view.ChannelFragmentUi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zxg on 2017/1/3.
 */
public class ChannelPresenter extends Presenter<ChannelFragmentUi>{

    private final static String TAG = ChannelPresenter.class.getSimpleName();
    private Context mContext;
    private ChannelModel mChannelModelImpl;
    public final static int  OTHER_CHANNEL= 0;
    public final static int  USER_CHANNEL= 1;
    public List<ChannelUpdateListener> listeners = new ArrayList<ChannelUpdateListener>();

    public void addListener(ChannelUpdateListener listener){
        listeners.add(listener);
    }

    public ChannelPresenter(){



    }
    public void setContext(Context context){
        this.mContext = context;
        addListener((ChannelUpdateListener)context);
        mChannelModelImpl = new ChannelModelImpl(mContext);
    }

    public List<Channel> getChannel(int flag){
        List<Channel> channelList;
        channelList = mChannelModelImpl.getChannelByFlag(flag);
        return channelList;
    }

    public void saveChannel(){
        mChannelModelImpl.deleteAllChannel();
        mChannelModelImpl.updateUserChannel(getUi().getUserChannelList());
        mChannelModelImpl.updateOtherChannel(getUi().getOtherChannelList());
    }

    public void onChannelUpdate(){
        for(ChannelUpdateListener listener:listeners){
            listener.onChannelUpdate();
        }
    }

    public interface ChannelUpdateListener{
        void onChannelUpdate();
    }
}
