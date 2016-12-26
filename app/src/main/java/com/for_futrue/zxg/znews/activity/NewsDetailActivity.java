package com.for_futrue.zxg.znews.activity;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.for_futrue.zxg.znews.Model.NewsModel;
import com.for_futrue.zxg.znews.R;
import com.for_futrue.zxg.znews.bean.News;
import com.for_futrue.zxg.znews.bean.NewsDetail;
import com.for_futrue.zxg.znews.presenter.NewsDetailPresenter;
import com.for_futrue.zxg.znews.presenter.NewsPresenter;
import com.for_futrue.zxg.znews.util.ImageLoaderUtil;
import com.for_futrue.zxg.znews.view.NewsDetailView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.sufficientlysecure.htmltextview.HtmlResImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

/**
 * Created by zxg on 2016/12/22.
 */
public class NewsDetailActivity extends BaseActivity<NewsDetailPresenter,NewsDetailView> implements NewsDetailView{

    private final static String TAG = NewsDetailActivity.class.getSimpleName();
    @ViewInject(R.id.news_title)
    private TextView titleTextView;

    @ViewInject(R.id.news_image)
    private ImageView newsImage;

//    @ViewInject(R.id.htNewsContent)
//    private HtmlTextView mTVNewsContent;

    @ViewInject(R.id.ic_favor)
    private Button actionFavor;

    @ViewInject(R.id.ic_report)
    private Button actionReport;

    @ViewInject(R.id.ic_repost)
    private Button actionRepost;

    @ViewInject(R.id.ic_comment)
    private Button actionComment;

    @ViewInject(R.id.htNewsContent1)
    private WebView webView;


    private News currentNews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG,"onCreate()...");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_detail);
        ViewUtils.inject(this);
        currentNews = (News)getIntent().getSerializableExtra("news");
        Log.i(TAG,"currentNews:"+currentNews.toString());
        initView();
    }

    private void initView(){
        titleTextView.setText(currentNews.getTitle());
        ImageLoaderUtil.display(newsImage, currentNews.getImgsrc());
        getPresenter().loadNewsDetail(currentNews.getDocid());
    }
    @Override
    NewsDetailView getUi() {
        return this;
    }

    @Override
    NewsDetailPresenter createPresenter() {
        return new NewsDetailPresenter();
    }

    @Override
    public NewsDetailPresenter getPresenter() {
        return super.getPresenter();
    }

    @Override
    public void showNewsDetialContent(String newsDetailContent) {
        Log.i(TAG, "newsDetailContent:" + newsDetailContent);
        WebSettings webSettings = webView.getSettings();
        webSettings.setSupportZoom(true);
        webSettings.setTextSize(WebSettings.TextSize.LARGER);
        webView.loadData(newsDetailContent,"text/html; charset=UTF-8",null);

    }

    @Override
    public void showLoadProgress(boolean isShow){

    }

    @Override
    public void showError(String error){

    }
}
