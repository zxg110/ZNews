package com.for_futrue.zxg.znews.datasource;

import android.os.Message;
import android.util.Log;

import com.for_futrue.zxg.znews.bean.News;
import com.for_futrue.zxg.znews.presenter.NewsPresenter;
import com.for_futrue.zxg.znews.util.OkHttpUtils;
import com.for_futrue.zxg.znews.util.UrlUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by zxg on 2016/4/5.
 */
public class NetDataSource implements NewsDataSource{
    @Override
    public void loadNewsListByChannel(String channel,final Message msg) {
        String url = "http://192.168.1.125:8080/news/News_loadNews.do?channel=2&currentPage=1";
        Log.i("zxg", "news list address:" + url);
        OkHttpUtils.ResultCallback<String> loadNewsCallback = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                Log.i("zxg1","response1:"+ response);
                response = response.replace("\\/","/");
                msg.obj = response;
                Gson gson = new Gson();
                List<News> newsList = gson.fromJson(response,new TypeToken<List<News>>(){}.getType());
                Log.i("zxg1","url: "+newsList.get(0).getSourceUrl());
                msg.what = NewsPresenter.GET_DATA_SUCCESS;
                msg.sendToTarget();
            }

            @Override
            public void onFailure(Exception e) {
                msg.obj = e;
                msg.what = NewsPresenter.GET_DATA_FAIL;
                msg.sendToTarget();
            }
        };
        OkHttpUtils.get(url,loadNewsCallback);

    }

    @Override
    public String getNewsContentByUrl(String url) {
        return null;
    }
}
