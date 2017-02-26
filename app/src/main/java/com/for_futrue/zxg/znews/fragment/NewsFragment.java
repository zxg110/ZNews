package com.for_futrue.zxg.znews.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.for_futrue.zxg.znews.R;
import com.for_futrue.zxg.znews.activity.NewsDetailActivity;
import com.for_futrue.zxg.znews.adapter.NewsItemAdapter;
import com.for_futrue.zxg.znews.adapter.NewsItemAdapter1;
import com.for_futrue.zxg.znews.adapter.NewsMainAdapter;
import com.for_futrue.zxg.znews.bean.News;
import com.for_futrue.zxg.znews.presenter.NewsPresenter;
import com.for_futrue.zxg.znews.view.NewsFragmentUi;
import com.for_futrue.zxg.znews.view.RecycleViewDivider;
import com.for_futrue.zxg.znews.view.RefreshRecyclerView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;
import android.os.Handler;
import android.widget.Toast;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;

/**
 * Created by zxg on 2016/3/29.
 */
public class NewsFragment extends BaseFragment<NewsPresenter,NewsFragmentUi> implements NewsFragmentUi{
    public static final String TAG = "NewsFragment";
    private int pageIndex = 0;

    private int newsDesc;

    @ViewInject(R.id.main_recyclerview)
    private RefreshRecyclerView mainRecyclerView;

    private NewsItemAdapter1 mainAdapter;

    @ViewInject(R.id.loading)
    private ProgressBar loading;

    @ViewInject(R.id.mPtrFrameLayout)
    private PtrClassicFrameLayout mPtrFrameLayout;

    private LinearLayoutManager mLayoutManager;

    private List<News> newsList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        Bundle args = getArguments();
        newsDesc = args != null?args.getInt("desc"):-1;
        Log.i(TAG, "newsDesc:" + newsDesc);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.news_fragment, null);
        ViewUtils.inject(this, view);
        mainAdapter = new NewsItemAdapter1(getActivity());
        mLayoutManager = new LinearLayoutManager(getActivity());
        mainRecyclerView.setLayoutManager(mLayoutManager);
        mainRecyclerView.setFooterResource(R.layout.item_footer);
        mainRecyclerView.setLoadMoreEnable(true);
        mainRecyclerView.setAdapter(mainAdapter);
        mainRecyclerView.setOnLoadMoreListener(new RefreshRecyclerView.OnLoadMoreListener() {
            @Override
            public void loadMoreListener() {
                loadMoreNews();
            }
        });


        initPtrFrameLayout();
        return view;
    }
    private void loadMoreNews(){
        pageIndex++;
        getmPresenter().loadNewsByChannel(newsDesc,pageIndex);

    }
    private void initPtrFrameLayout(){
        mPtrFrameLayout.setLastUpdateTimeRelateObject(this);
        mPtrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mainRecyclerView.setLoadMoreEnable(true);
                        getmPresenter().loadNewsByChannel(newsDesc,pageIndex);
                        mPtrFrameLayout.refreshComplete();
                    }
                }, 0);
            }
        });
    }
    /**
     *
     * @param isVisibleToUser: it is true when this fragment visible
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        /*
            loading data when this fragment visible and newList is null
         */
        if(isVisibleToUser && newsList == null){
            getmPresenter().loadNewsByChannel(newsDesc,pageIndex);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getmPresenter().setmContext(getActivity().getApplicationContext());

    }

    @Override
    NewsPresenter createPresenter() {
        return new NewsPresenter();
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

    @Override
    public void setNewsData(List<News> newsList1) {

        if(newsList1 == null || newsList1.size() == 0){
            Toast.makeText(getActivity(),"已加载全部",Toast.LENGTH_SHORT).show();
            mainRecyclerView.setLoadMoreEnable(false);
            mainRecyclerView.notifyData();
            pageIndex = 0;
            return;
        }
        if(newsList == null){
            newsList = new ArrayList<News>();
        }
        this.newsList.addAll(newsList1);
        if(pageIndex == 0){
            mainAdapter.setNewsList(this.newsList);
            mainRecyclerView.setAdapter(mainAdapter);
        }else{
            mainAdapter.notifyDataSetChanged();
        }
        mainRecyclerView.notifyData();
    }

    @Override
    public void showError(String errorInfo) {
        Toast.makeText(getContext(),errorInfo,Toast.LENGTH_SHORT);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
