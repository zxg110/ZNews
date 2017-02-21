package com.for_futrue.zxg.znews.view;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.for_futrue.zxg.znews.R;
import com.for_futrue.zxg.znews.util.DensityUtils;

/**
 * Created by zxg on 2017/1/18.
 */
public class PagerIndicator extends LinearLayout{

    private LayoutParams mLayoutParams;

    public PagerIndicator(Context context) {
        super(context);
        initLayoutParams();
    }

    public PagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayoutParams();
    }

    public PagerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayoutParams();
    }



    private void initLayoutParams(){
        mLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        mLayoutParams.setMargins(0, 0, (int) DensityUtils.dipToPx(2.5f), 0);
    }

    public int getPageCount(){
        return getChildCount();
    }

    public void setCurrentPage(int index){
        if(getChildCount() <= 0){
            return;
        }
        index %= getChildCount();
        for(int i=0;i<getChildCount();i++){
            View view = getChildAt(i);
            if(i == index){
                view.setSelected(true);
            }else {
                view.setSelected(false);
            }
        }
    }
    public void setTotalPageSize(int size){
        if(size == 1){
            removeAllViews();
            return;
        }
        if(size == getChildCount()){
            return;
        }
        if(size > getChildCount()){
            while(getChildCount() < size){
                ImageView imageView = new ImageView(getContext());
                imageView.setImageResource(R.drawable.pager_indicator);
                addView(imageView,mLayoutParams);

            }
        }else {
            while (getChildCount() > size){
                removeViewAt(getChildCount() -1);
            }
        }
    }
}
