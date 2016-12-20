package com.for_futrue.zxg.znews.datasource;

import android.os.Message;
import android.util.Log;

import com.for_futrue.zxg.znews.bean.JsonBean;
import com.for_futrue.zxg.znews.bean.News;
import com.for_futrue.zxg.znews.presenter.NewsPresenter;
import com.for_futrue.zxg.znews.util.JsonUtils;
import com.for_futrue.zxg.znews.util.OkHttpUtils;

import com.for_futrue.zxg.znews.util.UrlsUtils;
import com.for_futrue.zxg.znews.widget.NewsUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by zxg on 2016/4/5.
 */
public class NetDataSource implements NewsDataSource{
    final static String TAG = "NetDataSource";
    @Override
    public void loadNewsListByChannel(final int channel,final Message msg) {
        Log.i(TAG,"channel:"+channel);
        String url = UrlsUtils.getUrlByChannel(channel);
        OkHttpUtils.ResultCallback<String> loadNewsCallback = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                Log.i("NetDataSource","loadNewsCallback success response:"+ response);
                List<News> newsList = NewsUtil.readJsonNewsBeans(response,UrlsUtils.getIDByChannel(channel));
                Log.i("NetDataSource","size:"+newsList.size());
                msg.obj = newsList;
                msg.what = NewsPresenter.GET_DATA_SUCCESS;
                msg.sendToTarget();
            }

            @Override
            public void onFailure(Exception e) {
                Log.i(TAG,"EXCEPTION:"+e);
                msg.obj = e;
                msg.what = NewsPresenter.GET_DATA_FAIL;
                msg.sendToTarget();
            }
        };
        Log.i(TAG,"url:"+url);
        //url = "http://c.m.163.com/nc/article/list/T1397016069906/0-20.html";
        url = UrlsUtils.getUrlByChannel(channel);
        OkHttpUtils.get(url,loadNewsCallback);

    }

    @Override
    public String getNewsContentByUrl(String url) {
        return null;
    }
}
