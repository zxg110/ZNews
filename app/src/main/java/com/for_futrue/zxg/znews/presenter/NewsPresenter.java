package com.for_futrue.zxg.znews.presenter;

import android.content.Context;

import com.for_futrue.zxg.znews.bean.News;
import com.for_futrue.zxg.znews.view.NewsFragmentUi;

import java.util.List;

/**
 * Created by zxg on 2016/3/30.
 */
public class NewsPresenter extends Presenter<NewsFragmentUi>{
    private Context mContext;

    public NewsPresenter(Context context){
        this.mContext = context;
    }
    public List<News> getNewsListByChannel(){
        return null;
    }
}
