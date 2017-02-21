package com.for_futrue.zxg.znews.Model;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Message;
import android.util.Log;

import com.for_futrue.zxg.znews.datasource.DataSourceFactory;
import com.for_futrue.zxg.znews.datasource.NewsDataSource;

/**
 * Created by zxg on 2016/12/25.
 */
public class NewsModelImpl implements NewsModel{
    private NewsDataSource newsDataSource;
    public NewsModelImpl(){
        getNewsDataSource();
    }


    @Override
    public void loadNewsListByChannel(int channel, int index,Message msg) {
        newsDataSource.loadNewsListByChannel(channel,index,msg);
    }

    @Override
    public void loadNewsDetailByUrl(String docId, Message msg) {
        newsDataSource.getNewsContentByUrl(docId,msg);

    }
//    private boolean isNetworkAvaiable() {
//        Log.i("zxg1", "network context:" + mContext.toString());
//        Log.i("zxg1","isNetwork time:"+System.currentTimeMillis());
//        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService
//                (Context.CONNECTIVITY_SERVICE);
//        if(connectivityManager != null){
//            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
//            if(info != null && info.isConnected()){
//                return true;
//            }
//        }
//        return false;
//    }
    private void getNewsDataSource(){
//        if(isNetworkAvaiable()){
        newsDataSource = DataSourceFactory.getInstance(DataSourceFactory.NET_DATA_SOURCE);
//        }else{
//            newsDataSource = DataSourceFactory.getInstance(DataSourceFactory.DATABASE_SOURCE);
//        }
    }
}
