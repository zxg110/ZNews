package com.for_futrue.zxg.znews.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by zxg on 2016/3/29.
 */
public class NewsFragmentAdapter extends FragmentPagerAdapter{
    private List<Fragment> newsFragmentList;

    public NewsFragmentAdapter(FragmentManager fm,List<Fragment> list) {
        super(fm);
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
}
