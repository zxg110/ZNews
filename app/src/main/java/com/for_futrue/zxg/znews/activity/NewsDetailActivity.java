package com.for_futrue.zxg.znews.activity;

import android.os.Bundle;

import com.for_futrue.zxg.znews.bean.NewsDetail;
import com.for_futrue.zxg.znews.presenter.NewsDetailPresenter;
import com.for_futrue.zxg.znews.presenter.NewsPresenter;
import com.for_futrue.zxg.znews.view.NewsDetailView;

/**
 * Created by zxg on 2016/12/22.
 */
public class NewsDetailActivity extends BaseActivity<NewsDetailPresenter,NewsDetailView> implements NewsDetailView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    NewsDetailView getUi() {
        return null;
    }

    @Override
    NewsDetailPresenter createPresenter() {
        return new NewsDetailPresenter();
    }

    @Override
    public NewsDetailPresenter getPresenter() {
        return super.getPresenter();
    }
}
