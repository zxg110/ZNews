package com.for_futrue.zxg.znews.presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;

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
    private final static int GET_DATA_SUCCESS = 0;
    private final static int GET_DATA_FAIL = 1;
    private Context mContext;
    private NewsDataSource newsDataSource;
    private final Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case GET_DATA_SUCCESS:
                    getUi().showLoadingAnim(false);
                    getUi().setNewsData((List<News>)msg.obj);
                    break;
                case GET_DATA_FAIL:
                    getUi().showLoadingAnim(false);
                    getUi().showError((String)msg.obj);
            }
        }
    };
    public NewsPresenter(Context context) {
        this.mContext = context;
        getNewsDataSource();
    }

    public void loadNewsByChannel(String channelDesc) {
        Message msg = Message.obtain(mHandler);
        newsDataSource.getNewsListByChannel(channelDesc,msg);
    }

    private boolean isNetworkAvaiable() {
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
        if(isNetworkAvaiable()){
            newsDataSource = DataSourceFactory.getInstance(DataSourceFactory.NET_DATA_SOURCE);
        }else{
            newsDataSource = DataSourceFactory.getInstance(DataSourceFactory.DATABASE_SOURCE);
        }
    }
}
