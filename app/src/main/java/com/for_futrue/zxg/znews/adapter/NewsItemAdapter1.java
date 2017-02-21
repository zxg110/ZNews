package com.for_futrue.zxg.znews.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.for_futrue.zxg.znews.R;
import com.for_futrue.zxg.znews.activity.NewsDetailActivity;
import com.for_futrue.zxg.znews.bean.News;
import com.for_futrue.zxg.znews.util.ImageLoaderUtil;
import com.for_futrue.zxg.znews.util.ImageLoaderUtils;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.List;

/**
 * Created by zxg on 2017/1/16.
 */
public class NewsItemAdapter1 extends RecyclerView.Adapter<NewsItemAdapter1.NewsItemViewHolder> implements View.OnClickListener{

    private List<News> newsList;
    private LayoutInflater mInflater = null;
    private Context mContext;



    @Override
    public void onBindViewHolder(final NewsItemViewHolder holder, int position) {
        android.util.Log.i("Recycle", "onBindViewHolder");
        ImageLoaderUtils.display(mContext,  holder.leftImage, newsList.get(position).getImgsrc());
        holder.newsTitle.setText(newsList.get(position).getTitle());
        holder.newsDesc.setText(newsList.get(position).getDigest());
        holder.newsAddress.setText(newsList.get(position).getSource());
        holder.itemView.setTag(position);
    }
    public void setNewsList(List<News> newsList){
        this.newsList = newsList;
        this.notifyDataSetChanged();
    }
    public List<News> getNewsList(){
        return newsList;
    }

    public NewsItemAdapter1(Context context){
        mInflater = LayoutInflater.from(context);
        mContext = context;

    }
    @Override
    public int getItemCount() {
        return  newsList == null ? 0:newsList.size();
    }

    @Override
    public NewsItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.news_item,parent,false);
        view.setOnClickListener(this);
        NewsItemViewHolder holder = new NewsItemViewHolder(view);
        return holder;
    }

    public static class NewsItemViewHolder extends ViewHolder{

         public NewsItemViewHolder(View view){
             super(view);
             leftImage = (ImageView)view.findViewById(R.id.left_image);
             newsDesc = (TextView)view.findViewById(R.id.news_desc);
             newsTitle = (TextView)view.findViewById(R.id.news_title);
             newsAddress = (TextView)view.findViewById(R.id.news_address);
         }

        ImageView leftImage;
        TextView newsTitle;
        TextView newsDesc;
        TextView newsAddress;

    }

    @Override
    public void onClick(View v) {
        android.util.Log.i("zxg1111","adapter  onclick view:"+v.getTag());
        int position = (int)v.getTag();
        News news = newsList.get(position);
        Intent intent = new Intent(mContext, NewsDetailActivity.class);
        intent.putExtra("news",news);
        mContext.startActivity(intent);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
