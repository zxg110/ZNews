package com.for_futrue.zxg.znews.fragment;

import android.os.Bundle;
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

/**
 * Created by zxg on 2016/3/29.
 */
public class NewsFragment extends BaseFragment<NewsPresenter,NewsFragmentUi> implements NewsFragmentUi{
    private String newsDesc;

    @ViewInject(R.id.news_listview)
    private ListView newsListView;

    @ViewInject(R.id.loading)
    private ProgressBar loading;

    @ViewInject(R.id.test_text)
    private TextView testText;

    private List<News> newsList;


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
        ViewUtils.inject(this,view);
        testText.setText(newsDesc);
        return view;
    }

    @Override
    NewsPresenter createPresenter() {
        return new NewsPresenter(getActivity());
    }

    @Override
    NewsFragmentUi getUi() {
        return this;
    }
}
