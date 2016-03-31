package com.for_futrue.zxg.znews.fragment;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.for_futrue.zxg.znews.R;
import com.for_futrue.zxg.znews.bean.News;
import com.for_futrue.zxg.znews.presenter.NewsPresenter;
import com.for_futrue.zxg.znews.view.NewsFragmentUi;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.List;
import android.os.Handler;

/**
 * Created by zxg on 2016/3/29.
 */
public class NewsFragment extends BaseFragment<NewsPresenter,NewsFragmentUi> implements NewsFragmentUi{
    private final static int SET_NEWS_DATA = 0;

    private String newsDesc;

    @ViewInject(R.id.news_listview)
    private ListView newsListView;

    @ViewInject(R.id.loading)
    private ProgressBar loading;

    @ViewInject(R.id.test_text)
    private TextView testText;

    private List<News> newsList;

    private final Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case SET_NEWS_DATA:

                break;
            }
        }
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Bundle args = getArguments();
        newsDesc = args != null?args.getString("desc"):"error";
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.news_fragment,null);
        ViewUtils.inject(this, view);
        testText.setText(newsDesc);

        return view;
    }

    /**
     *
     * @param isVisibleToUser: it is true when this fragment visible
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        /*
            loading data when this fragment visible
         */
        if(isVisibleToUser){

        }
    }


    @Override
    NewsPresenter createPresenter() {
        return new NewsPresenter(getActivity());
    }

    @Override
    NewsFragmentUi getUi() {
        return this;
    }

    @Override
    public void showLoadingAnim(boolean isShow) {
        if(isShow){
            loading.setVisibility(View.VISIBLE);
        }else{
            loading.setVisibility(View.INVISIBLE);
        }
    }
}
