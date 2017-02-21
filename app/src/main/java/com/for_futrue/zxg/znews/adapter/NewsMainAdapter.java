package com.for_futrue.zxg.znews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.for_futrue.zxg.znews.R;
import com.for_futrue.zxg.znews.bean.News;
import com.for_futrue.zxg.znews.view.AutoSlideViewpager;
import com.for_futrue.zxg.znews.view.PagerIndicator;

import java.util.List;

/**
 * Created by zxg on 2017/1/18.
 */
public class NewsMainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final  int VIEW_TYPE_BANNER = 1;
    private static final int VIEW_TYPE_NEWS = 2;
    private Context mContext;


    private List<News> mBannerNewsList;
    private List<News> mCommonNewsList;

//    private OnItemClickLisenter mOnItemClickListener;
    public void setBannerNewsList(List<News> bannerNewsList){
        this.mBannerNewsList = bannerNewsList;
        notifyDataSetChanged();
    }
    public void setCommonNewsList(List<News> commonNewsList){
        this.mCommonNewsList = commonNewsList;
        notifyDataSetChanged();
    }
    public NewsMainAdapter(Context context){
        mContext = context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        RecyclerView.ViewHolder viewHolder = null;
        View view;
        switch (viewType){
            case VIEW_TYPE_BANNER:
                view = inflater.inflate(R.layout.news_branner,parent,false);
                viewHolder = new BannerViewHolder(view,mContext);
                break;
            case VIEW_TYPE_NEWS:
                view = inflater.inflate(R.layout.news_recyclerview,parent,false);
                viewHolder = new NewsViewHolder(view,mContext);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType){
            case VIEW_TYPE_BANNER:
                if(holder instanceof BannerViewHolder){
                    bindBannerView((BannerViewHolder)holder,mBannerNewsList);
                }
                break;
            case VIEW_TYPE_NEWS:
                if(holder instanceof NewsViewHolder){
                    bindCommonView((NewsViewHolder)holder,mCommonNewsList);
                }
                break;
        }
    }
    private void bindCommonView(NewsViewHolder holder,List<News> commonNewsList){
        if(holder.newsItemAdapter1 != null && holder.newsItemAdapter1.getNewsList() != commonNewsList){
            holder.newsItemAdapter1.setNewsList(commonNewsList);
            holder.newsItemAdapter1.notifyDataSetChanged();
        }
    }
    private void bindBannerView(BannerViewHolder holder,List<News> bannerNewsList){
        if(holder.bannerAdapter != null && holder.bannerAdapter.getNewsList() != bannerNewsList){
            holder.indicatorContainer.setTotalPageSize(bannerNewsList.size());
            holder.indicatorContainer.setCurrentPage(0);
            holder.bannerAdapter.setData(bannerNewsList);
            holder.bannerPager.dataSetChanged();
        }
    }
    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return VIEW_TYPE_BANNER;
        } else{
            android.util.Log.i("Recycle","get view_type_news");
            return VIEW_TYPE_NEWS;
        }
    }


    public static class BannerViewHolder extends RecyclerView.ViewHolder{
        public AutoSlideViewpager bannerPager;
        public PagerIndicator indicatorContainer;
        private NewsBannerAdapter bannerAdapter;

        public BannerViewHolder(View itemView,Context context){
            super(itemView);
            bannerPager = (AutoSlideViewpager)itemView.findViewById(R.id.banner_pager);
            indicatorContainer = (PagerIndicator)itemView.findViewById(R.id.indicator_container);
            bannerPager.setPagerIndicator(indicatorContainer);
            bannerAdapter = new NewsBannerAdapter(context);
            bannerPager.setAdapter(bannerAdapter);
        }
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder{
        public RecyclerView newsItemView;
        public NewsItemAdapter1 newsItemAdapter1;

        public NewsViewHolder(View itemView,Context context){
            super(itemView);
            newsItemView = (RecyclerView)itemView;
            newsItemAdapter1 = new NewsItemAdapter1(context);
            newsItemView.setAdapter(newsItemAdapter1);
        }
    }
//    public interface OnItemClickLisenter{
//        void onClick();
//    }
//    public void setOnItemClickListener(OnItemClickLisenter onItemClickListener){
//        this.mOnItemClickListener = onItemClickListener;
//    }
}
