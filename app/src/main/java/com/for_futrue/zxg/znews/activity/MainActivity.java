package com.for_futrue.zxg.znews.activity;


import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.for_futrue.zxg.znews.R;
import com.for_futrue.zxg.znews.adapter.NewsFragmentAdapter;
import com.for_futrue.zxg.znews.bean.Channel;
import com.for_futrue.zxg.znews.fragment.ChannelFragment;
import com.for_futrue.zxg.znews.fragment.NewsFragment;
import com.for_futrue.zxg.znews.presenter.MainPresenter;
import com.for_futrue.zxg.znews.view.MainNewsView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<MainPresenter, MainNewsView> implements
        MainNewsView {

    private static final String TAG = MainActivity.class.getSimpleName();

    @ViewInject(R.id.channel_content_layout)
    private LinearLayout channelContent;

    @ViewInject(R.id.channel_scroll_view)
    private HorizontalScrollView channelScrollView;

    @ViewInject(R.id.mViewPager)
    private ViewPager fragmentViewPager;

    @ViewInject(R.id.btn_more_channel)
    private ImageView mAddChannel;


    private int channelSelectedIndex = 0;

    private List<Fragment> newsFragmentList = new ArrayList<Fragment>();

    private List<Channel> channelList;

    private ChannelFragment mChannelFragment;

    private int mScreenWidth;

    private static final String TAG_CHANNEL_FRAGMENT = "tag_channel_fragment";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        ViewUtils.inject(this);
        initChannelTab();
        initFragment();
    }

    @OnClick(R.id.btn_more_channel)
    private void moreCategoryClick(View v) {
        Log.i("zxg", "more category click");
        showFragment(TAG_CHANNEL_FRAGMENT,true,true);

    }

    @Override
    MainNewsView getUi() {
        return this;
    }

    @Override
    MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    private void initChannelTab() {
        channelContent.removeAllViews();
        channelList = getPresenter().getUserChannel();
        for (int i = 0; i < channelList.size(); i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(getPresenter()
                    .getWindowsWidth(this) / 7, LinearLayout.LayoutParams
                    .WRAP_CONTENT);
            params.leftMargin = 5;
            params.rightMargin = 5;
            final TextView channelTextView = new TextView(this);
            channelTextView.setTextAppearance(this, R.style.top_category_scroll_view_item_text);
            channelTextView.setGravity(Gravity.CENTER);
            channelTextView.setPadding(20, 20, 40, 40);
            channelTextView.setId(i);
            channelTextView.setText(channelList.get(i).getName());
            if (i == channelSelectedIndex) {
                channelTextView.setSelected(true);
                channelTextView.setTextAppearance(this, R.style
                        .top_category_scroll_view_item_text_selected);
            }
            channelTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < channelContent.getChildCount(); i++) {
                        TextView localView = (TextView) channelContent.getChildAt(i);
                        if (localView != v) {
                            localView.setSelected(false);
                            localView.setTextAppearance(MainActivity.this, R.style
                                    .top_category_scroll_view_item_text);
                        } else {
                            localView.setSelected(true);
                            localView.setTextAppearance(MainActivity.this, R.style
                                    .top_category_scroll_view_item_text_selected);
                            fragmentViewPager.setCurrentItem(channelTextView.getId());


                        }
                    }
                }
            });
            channelContent.addView(channelTextView);
        }
    }

    private void initFragment() {
        newsFragmentList.clear();
        for (Channel channel : channelList) {
            Bundle data = new Bundle();
            data.putInt("desc", channel.getDesc());
            NewsFragment newsFragment = new NewsFragment();
            newsFragment.setArguments(data);
            newsFragmentList.add(newsFragment);
        }
        NewsFragmentAdapter mAdapter = new NewsFragmentAdapter(getSupportFragmentManager(),
                newsFragmentList);
        fragmentViewPager.setAdapter(mAdapter);
        fragmentViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int
                    positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                fragmentViewPager.setCurrentItem(position);
                selectTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void selectTab(int tabPosition) {

        for (int i = 0; i < channelContent.getChildCount(); i++) {
            View checkView = channelContent.getChildAt(tabPosition);
            int k = checkView.getMeasuredWidth();
            int l = checkView.getLeft();
            int i2 = l + k / 2 - getPresenter().getWindowsWidth(this) / 2;
            channelScrollView.smoothScrollTo(i2, 0);

        }

        for (int i = 0; i < channelContent.getChildCount(); i++) {
            TextView checkView = (TextView) channelContent.getChildAt(i);
            if (i == tabPosition) {
                checkView.setSelected(true);
                checkView.setTextAppearance(MainActivity.this, R.style
                        .top_category_scroll_view_item_text_selected);
            } else {
                checkView.setSelected(false);
                checkView.setTextAppearance(MainActivity.this, R.style
                        .top_category_scroll_view_item_text);

            }
        }
    }

    private void showFragment(String tag,boolean show,boolean executeImmediately){
        final FragmentManager fm = getSupportFragmentManager();

        if(fm == null){
            Log.i(TAG,"Fragment manager is null"+tag);
            return;
        }
        Fragment fragment = fm.findFragmentByTag(tag);
        if(!show && fragment == null){
            return;
        }

        final FragmentTransaction transaction = fm.beginTransaction();
        if(show){
            if(fragment == null){
                fragment = createNewFragmentForTag(tag);
                transaction.add(getContainerIdForFragment(tag),fragment,tag);
            }else{
                transaction.show(fragment);
            }
        }else {
            transaction.hide(fragment);
        }
        transaction.commitAllowingStateLoss();
        if(executeImmediately){
            Log.i("zxg33","execute");
            fm.executePendingTransactions();
        }
    }
    private Fragment createNewFragmentForTag(String tag){
        if(TAG_CHANNEL_FRAGMENT.equals(tag)){
            mChannelFragment = new ChannelFragment();
            return mChannelFragment;
        }
        throw new IllegalStateException("Unexcepted fragment: "+tag);
    }

    private int getContainerIdForFragment(String tag){
        if(TAG_CHANNEL_FRAGMENT.equals(tag)){
            return R.id.main_activity;
        }
        throw new IllegalStateException("Unexcepted fragment: "+tag);
    }

}
