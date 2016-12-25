package com.for_futrue.zxg.znews.presenter;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.for_futrue.zxg.znews.Model.NewsModel;
import com.for_futrue.zxg.znews.Model.NewsModelImpl;
import com.for_futrue.zxg.znews.bean.NewsDetail;
import com.for_futrue.zxg.znews.view.NewsDetailView;

/**
 * Created by zxg on 2016/12/22.
 */
public class NewsDetailPresenter extends Presenter<NewsDetailView>{

    public final static int GET_DATA_SUCCESS = 0;
    public final static int GET_DATA_FAIL = 1;
    public NewsModel newsModelImpl;

    public NewsDetailPresenter(){
        this.newsModelImpl = new NewsModelImpl();
    }
    private final Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case GET_DATA_SUCCESS:
                    NewsDetail newsDetail = (NewsDetail)msg.obj;
                    Log.i("zxg3","newsDetail1:"+newsDetail);
                    getUi().showNewsDetialContent(newsDetail.getBody());
                    getUi().showLoadProgress(false);
                    break;
                case GET_DATA_FAIL:
                    getUi().showError((String)msg.obj);
            }
        }
    };

    public void loadNewsDetail(String docId){
        getUi().showLoadProgress(true);
        Message message = Message.obtain(mHandler);
        newsModelImpl.loadNewsDetailByUrl(docId,message);

    }
}
