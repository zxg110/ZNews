package com.for_futrue.zxg.znews.database;

import android.content.Context;
import android.util.Log;

import com.for_futrue.zxg.znews.R;
import com.for_futrue.zxg.znews.bean.Channel;
import com.for_futrue.zxg.znews.presenter.ChannelPresenter;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by zxg on 2016/12/29.
 */
public class ChannelDao {
    private Context context;
    private Dao<Channel,Integer> channelDao;
    private NewsDatabaseHelper helper;

    public ChannelDao(Context context){
        this.context = context;
        try{
            helper = NewsDatabaseHelper.getHelper(context);
            Log.i("zxg111","helper:"+helper);
            channelDao = helper.getDao(Channel.class);
            Log.i("zxg111","channelDao:"+channelDao);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void add(Channel channel){
        try {
            channelDao.create(channel);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void update(Channel channel){
        try {
            channelDao.update(channel);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Channel> queryChannelBySelected(int flag){
        try{
            List<Channel> channelList;
            if(flag == ChannelPresenter.USER_CHANNEL){
                channelList = channelDao.queryBuilder().where().eq("selected",1).query();
            }else{
                channelList = channelDao.queryBuilder().where().eq("selected",0).query();
            }

            return channelList;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}
