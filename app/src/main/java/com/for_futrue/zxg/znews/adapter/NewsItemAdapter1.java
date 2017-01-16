package com.for_futrue.zxg.znews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.for_futrue.zxg.znews.R;
import com.for_futrue.zxg.znews.bean.News;
import com.for_futrue.zxg.znews.util.ImageLoaderUtil;

import java.util.List;

/**
 * Created by zxg on 2017/1/16.
 */
public class NewsItemAdapter1 extends RecyclerView.Adapter<NewsItemAdapter1.NewsItemViewHolder>{

    private List<News> newsList;
    private LayoutInflater mInflater = null;


    @Override
    public void onBindViewHolder(NewsItemViewHolder holder, int position) {
        ImageLoaderUtil.display(holder.leftImage,newsList.get(position).getImgsrc());
        holder.newsTitle.setText(newsList.get(position).getTitle());
        holder.newsDesc.setText(newsList.get(position).getDigest());
        holder.newsAddress.setText(newsList.get(position).getSource());
    }

    public NewsItemAdapter1(Context context){
        mInflater = LayoutInflater.from(context);
    }
    @Override
    public int getItemCount() {
        return newsList.size();
    }

    @Override
    public NewsItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        NewsItemViewHolder holder = new NewsItemViewHolder(mInflater.inflate(R.layout.news_item,parent,false));
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
}
