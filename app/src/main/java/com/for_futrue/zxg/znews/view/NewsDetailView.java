package com.for_futrue.zxg.znews.view;

/**
 * Created by zxg on 2016/12/22.
 */
public interface NewsDetailView extends  Ui{
    public void showNewsDetialContent(String newsDetailContent);

    public void showLoadProgress(boolean isShow);

    public void showError(String error);
}
