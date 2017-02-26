package com.for_futrue.zxg.znews.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.for_futrue.zxg.znews.R;
import com.for_futrue.zxg.znews.bean.News;
import com.for_futrue.zxg.znews.presenter.NewsDetailPresenter;
import com.for_futrue.zxg.znews.util.ImageLoaderUtils;
import com.for_futrue.zxg.znews.view.NewsDetailView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * Created by zxg on 2016/12/22.
 */
public class NewsDetailActivity extends BaseActivity<NewsDetailPresenter,NewsDetailView> implements NewsDetailView,View.OnClickListener{

    private final static String TAG = NewsDetailActivity.class.getSimpleName();
    @ViewInject(R.id.news_title)
    private TextView titleTextView;

    @ViewInject(R.id.news_image)
    private ImageView newsImage;

    @ViewInject(R.id.detail_head_layout)
    private RelativeLayout mDetailHeadLayout;

    @ViewInject(R.id.ic_favor)
    private ImageView mFavorImage;

    @ViewInject(R.id.ic_report)
    private ImageView mReportImage;

    @ViewInject(R.id.ic_repost)
    private ImageView mRepostImage;

    @ViewInject(R.id.ic_comment)
    private ImageView mCommentImage;

    @ViewInject(R.id.webView)
    private WebView webView;

    @ViewInject(R.id.btn_back)
    private ImageView mBackImage;

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
        mFavorImage.setOnClickListener(this);
        mReportImage.setOnClickListener(this);
        mRepostImage.setOnClickListener(this);
        mCommentImage.setOnClickListener(this);
        mBackImage.setOnClickListener(this);
        titleTextView.setText(currentNews.getTitle());
        ImageLoaderUtils.display1(this, newsImage, currentNews.getImgsrc());
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
        webView.setBackgroundColor(this.getResources().getColor(R.color.activity_bg_color1)); // 设置背景色
//        webView.getBackground().setAlpha(2);
        webView.loadData(newsDetailContent,"text/html; charset=UTF-8",null);

    }

    @Override
    public void showLoadProgress(boolean isShow){

    }

    @Override
    public void showError(String error){
        Toast.makeText(this,"服务器异常",Toast.LENGTH_SHORT).show();
    }

    private void onFavorImageClicked(){
        mFavorImage.setSelected(true);
    }
    private void onCommentImageClicked(){

    }
    private void onReportImageClicked(){

    }
    private void onRepostImageClicked(){

    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        Log.i(TAG,"onClick(View"+ v+", id "+id+")...");
        switch (id){
            case R.id.ic_comment:
                onCommentImageClicked();
                break;
            case R.id.ic_favor:
                onFavorImageClicked();
                break;
            case R.id.ic_repost:
                onRepostImageClicked();
                break;
            case R.id.ic_report:
                onReportImageClicked();
                break;
            case R.id.btn_back:
                onBackPressed();
        }
    }
}
