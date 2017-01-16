package com.for_futrue.zxg.znews.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.for_futrue.zxg.znews.fragment.NewsFragment;

import java.util.List;

/**
 * Created by zxg on 2016/3/29.
 */
public class NewsFragmentAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> newsFragmentList;

    public NewsFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setList(List<Fragment> list){
        this.newsFragmentList = list;
    }
    @Override
    public Fragment getItem(int position) {
        return newsFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return newsFragmentList.size();
    }
    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        Object obj = super.instantiateItem(container, position);
        return obj;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }


}
