package com.for_futrue.zxg.znews.presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.for_futrue.zxg.znews.bean.News;
import com.for_futrue.zxg.znews.datasource.DataSourceFactory;
import com.for_futrue.zxg.znews.datasource.NewsDataSource;
import com.for_futrue.zxg.znews.view.NewsFragmentUi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zxg on 2016/3/30.
 */
public class NewsPresenter extends Presenter<NewsFragmentUi> {
    public final static int GET_DATA_SUCCESS = 0;
    public final static int GET_DATA_FAIL = 1;
    private Context mContext;
    private NewsDataSource newsDataSource;
    private final Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case GET_DATA_SUCCESS:
//                    getUi().showLoadingAnim(false);
//                    getUi().setNewsData((List<News>)msg.obj);
                    Log.i("zxg","response:"+msg.obj);
                    break;
                case GET_DATA_FAIL:
                    getUi().showLoadingAnim(false);
                    getUi().showError((String)msg.obj);
            }
        }
    };
    public NewsPresenter() {
    }

    public void loadNewsByChannel(String channelDesc) {
        if(newsDataSource == null){
            getNewsDataSource();
        }
        Message msg = Message.obtain(mHandler);
        newsDataSource.loadNewsListByChannel(channelDesc, msg);
    }

    private boolean isNetworkAvaiable() {
        Log.i("zxg1","network context:"+mContext.toString());
        Log.i("zxg1","isNetwork time:"+System.currentTimeMillis());
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService
                (Context.CONNECTIVITY_SERVICE);
        if(connectivityManager != null){
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if(info != null && info.isConnected()){
                return true;
            }
        }
        return false;
    }
    private void getNewsDataSource(){
//        if(isNetworkAvaiable()){
            newsDataSource = DataSourceFactory.getInstance(DataSourceFactory.NET_DATA_SOURCE);
//        }else{
//            newsDataSource = DataSourceFactory.getInstance(DataSourceFactory.DATABASE_SOURCE);
//        }
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }
}
