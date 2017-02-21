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
    private final static int SET_NEWS_DATA = 0;
    private final static int GET_NEWS_DATA = 1;
    private int pageIndex = 0;

    private int newsDesc;

//    @ViewInject(R.id.news_listview)
//    private ListView newsListView;

    @ViewInject(R.id.main_recyclerview)
    private RefreshRecyclerView mainRecyclerView;

    private NewsItemAdapter1 mainAdapter;

    @ViewInject(R.id.loading)
    private ProgressBar loading;

    @ViewInject(R.id.mPtrFrameLayout)
    private PtrClassicFrameLayout mPtrFrameLayout;

    private LinearLayoutManager mLayoutManager;



    private List<News> newsList;

    private NewsItemAdapter mAdapter;

    private final Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case SET_NEWS_DATA:
                    Log.i(TAG,"handler GET_NEWS_DTAT ");
//                    newsListView.setAdapter(mAdapter);
//                    newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                            News news = (News)mAdapter.getItem(position);
//                            Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
//                            intent.putExtra("news",news);
//                            startActivity(intent);
//                        }
//                    });

                    mainRecyclerView.notifyData();

                break;
                case GET_NEWS_DATA:
                    Log.i(TAG,"handler SET_NEWS_DATA newsDesc"+newsDesc);
                    getmPresenter().loadNewsByChannel(newsDesc,pageIndex);
            }
        }
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i("zxg333","onCreate");

        Bundle args = getArguments();
        newsDesc = args != null?args.getInt("desc"):-1;
        Log.i(TAG, "newsDesc:" + newsDesc);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        Log.i("zxg333", "onCreateView");
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.news_fragment, null);
        ViewUtils.inject(this, view);
        //mAdapter = new NewsItemAdapter(getActivity());
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
        android.util.Log.i("zxg9","loadMoreNews before ++:"+pageIndex);

        mHandler.obtainMessage(GET_NEWS_DATA).sendToTarget();
        pageIndex++;
        android.util.Log.i("zxg9","loadMoreNews after ++:"+pageIndex);

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
                        mHandler.obtainMessage(GET_NEWS_DATA).sendToTarget();
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
        Log.i("zxg333","setUserVisibleHint:"+newsDesc+" "+isVisibleToUser);
        super.setUserVisibleHint(isVisibleToUser);
        /*
            loading data when this fragment visible and newList is null
         */
        if(newsList != null){
            Log.i("zxg333","newsList.size:"+newsList.size());
        }

        if(isVisibleToUser && newsList == null){
            mHandler.obtainMessage(GET_NEWS_DATA).sendToTarget();
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("zxg1","context time:"+System.currentTimeMillis());
        Log.i("zxg1","context: "+getActivity().getApplicationContext().toString());
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
        if(newsList != null){
            outState.putString("test","1");
        }
    }
}
