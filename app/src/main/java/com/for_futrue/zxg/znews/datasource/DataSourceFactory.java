package com.for_futrue.zxg.znews.datasource;

/**
 * Created by zxg on 2016/4/5.
 */
public class DataSourceFactory {
    public final static int NET_DATA_SOURCE = 0;
    public final static int DATABASE_SOURCE = 1;

    public static NewsDataSource getInstance(int type){
        if(type == NET_DATA_SOURCE){
            return new NetDataSource();
        }else if(type == DATABASE_SOURCE){
            return new DataBaseSource();
        }
        return null;
    }
}
