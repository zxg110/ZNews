package com.for_futrue.zxg.znews.view;

import com.for_futrue.zxg.znews.bean.News;

import java.util.List;

/**
 * Created by zxg on 2016/3/30.
 */
public interface NewsFragmentUi extends Ui{
    void showLoadingAnim(boolean isShow);

    void setNewsData(List<News> newsList);

    void showError(String errorInfo);
}
