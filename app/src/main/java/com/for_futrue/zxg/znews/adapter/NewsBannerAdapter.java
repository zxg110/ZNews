package com.for_futrue.zxg.znews.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.for_futrue.zxg.znews.R;
import com.for_futrue.zxg.znews.activity.NewsDetailActivity;
import com.for_futrue.zxg.znews.bean.News;
import com.for_futrue.zxg.znews.util.ImageLoaderUtils;

import java.util.List;

/**
 * Created by zxg on 2017/1/19.
 */
public class NewsBannerAdapter extends PagerAdapter implements View.OnClickListener {
    private List<News> newsList;
    private LayoutInflater mInflater = null;
    private Context mContext;

    @Override
    public void onClick(View v) {
        int position = (int)v.getTag();
        News news = newsList.get(position);
        Intent intent = new Intent(mContext, NewsDetailActivity.class);
        intent.putExtra("news",news);
        mContext.startActivity(intent);
    }
    public NewsBannerAdapter(Context context){
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }
    public void setData(List<News> newsList){
        this.newsList = newsList;
        notifyDataSetChanged();
    }
    public List<News> getNewsList(){
        return newsList;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mInflater.inflate(R.layout.news_branner_item,null);
        ImageView newsImage = (ImageView)view.findViewById(R.id.news_branner_image);
        ImageLoaderUtils.display(mContext,newsImage,newsList.get(position).getImgsrc());
        TextView newsTitle = (TextView)view.findViewById(R.id.news_branner_title);
        newsTitle.setText(newsList.get(position).getTitle());
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return newsList == null ? 0:newsList.size();

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}
