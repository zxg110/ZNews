package com.for_futrue.zxg.znews.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.for_futrue.zxg.znews.bean.Channel;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zxg on 2016/12/28.
 */
public class NewsDatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DB_NAME = "news.ab";

    private static final int DB_VERSION = 1;

    private Map<String,Dao> daos = new HashMap<String,Dao>();

    private static NewsDatabaseHelper instance;

    public static synchronized NewsDatabaseHelper getHelper(Context context){
        context = context.getApplicationContext();
        if(instance == null){
            synchronized (NewsDatabaseHelper.class){
                instance = new NewsDatabaseHelper(context);
            }
        }
        return instance;
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try{
            TableUtils.createTable(connectionSource, Channel.class);
            initChannelData();
        }catch (Exception e){

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int
            i, int i1) {
        try{
            TableUtils.dropTable(connectionSource, Channel.class, true);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 通过类来获得指定的Dao
     */
    public synchronized Dao getDao(Class clazz) throws SQLException {
        Dao dao = null;
        String className = clazz.getSimpleName();
        if (daos.containsKey(className)) {
            dao = super.getDao(clazz);
            daos.put(className, dao);
        }
        return dao;
    }
    /**
     * 释放资源
     */
    @Override
    public void close() {
        super.close();
        for (String key : daos.keySet()) {
            Dao dao = daos.get(key);
            dao = null;
        }
    }
    public NewsDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    private void initChannelData(){
        try {
            Dao<Channel,Integer> channelDao = getDao(Channel.class);

        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
