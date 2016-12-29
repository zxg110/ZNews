package com.for_futrue.zxg.znews.database;

import android.content.Context;

import com.for_futrue.zxg.znews.bean.Channel;
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
            channelDao = helper.getDao(Channel.class);
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

    public List<Channel> queryChannelBySelected(){
        try{
            List<Channel> selectedChannelList;
            selectedChannelList = channelDao.queryBuilder().where().eq("selected",1).query();
            return selectedChannelList;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
