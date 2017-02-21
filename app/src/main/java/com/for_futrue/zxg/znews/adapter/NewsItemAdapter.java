package com.for_futrue.zxg.znews.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.for_futrue.zxg.znews.R;
import com.for_futrue.zxg.znews.bean.News;
import com.for_futrue.zxg.znews.util.ImageLoaderOption;
import com.for_futrue.zxg.znews.util.ImageLoaderUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by zxg on 2016/3/31.
 */
public class NewsItemAdapter extends BaseAdapter{
    private Context mContext;
    private List<News> newsList;
    private LayoutInflater mInflater = null;
    public NewsItemAdapter(Context context){
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
    }
    public void setNewsData(List<News> newsList){
        this.newsList = newsList;

        Log.i("zxg","adapter size:"+newsList.size());
    }
    @Override
    public int getCount() {
        return newsList == null? 0: newsList.size();
    }

    @Override
    public Object getItem(int position) {
        if(newsList != null && newsList.size() != 0){
            return newsList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        News currentNews = newsList.get(position);
        ViewHolder holder = null;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.news_item,null);
            holder = new ViewHolder();
            holder.leftImage = (ImageView)convertView.findViewById(R.id.left_image);
            holder.newsTitle = (TextView)convertView.findViewById(R.id.news_title);
            holder.newsDesc = (TextView)convertView.findViewById(R.id.news_desc);
            holder.newsAddress = (TextView)convertView.findViewById(R.id.news_address);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        holder.newsTitle.setText(newsList.get(position).getTitle());
        holder.newsDesc.setText(newsList.get(position).getDigest());
        holder.newsAddress.setText(newsList.get(position).getSource());
        ImageLoaderUtil.display(holder.leftImage, newsList.get(position).getImgsrc(), new ImageLoadingListener() {

            @Override
            public void onLoadingStarted(String s, View view) {

            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {

            }

            @Override
            public void onLoadingCancelled(String s, View view) {

            }
        });
        return convertView;
    }


    static class ViewHolder {
        public ImageView leftImage;
        public TextView newsTitle;
        public TextView newsDesc;
        public TextView newsAddress;

    }
}
