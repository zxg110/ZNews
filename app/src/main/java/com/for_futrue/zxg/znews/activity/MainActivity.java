package com.for_futrue.zxg.znews.activity;


import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.for_futrue.zxg.znews.R;
import com.for_futrue.zxg.znews.bean.Channel;
import com.for_futrue.zxg.znews.presenter.NewsPresenter;
import com.for_futrue.zxg.znews.view.MainNewsView;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.List;

public class MainActivity extends BaseActivity<NewsPresenter, MainNewsView> implements
        MainNewsView {

    @ViewInject(R.id.channel_content_layout)
    private LinearLayout channelContent;

    @ViewInject(R.id.channel_scroll_view)
    private HorizontalScrollView channelScrollView;

    private int channelSelectedIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        channelContent = (LinearLayout)findViewById(R.id.channel_content_layout);
//        Log.i("zxg","channelScrollView:"+channelScrollView.toString());
        initChannelTab();
    }

    @Override
    MainNewsView getUi() {
        return this;
    }

    @Override
    NewsPresenter createPresenter() {
        return new NewsPresenter(this);
    }

    private void initChannelTab() {
        channelContent.removeAllViews();
        List<Channel> userChannel = getPresenter().getUserChannel();
        Log.i("zxg","listSize:"+userChannel.size());
        for (int i = 0; i < userChannel.size(); i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) getResources()
                    .getDimension(R.dimen.channel_textview_width), LinearLayout.LayoutParams
                    .WRAP_CONTENT);
            params.leftMargin = 5;
            params.rightMargin = 5;
            final TextView channelTextView = new TextView(this);
            channelTextView.setTextAppearance(this, R.style.top_category_scroll_view_item_text);
            channelTextView.setGravity(Gravity.CENTER);
            channelTextView.setPadding(20, 20, 40, 40);
            channelTextView.setId(i);

            channelTextView.setText(userChannel.get(i).getName());
            if(i == channelSelectedIndex){
                channelTextView.setSelected(true);
                channelTextView.setTextColor(getResources().getColor(R.color
                        .channel_text_selected));
                channelTextView.setTextSize(getResources().getDimension(R.dimen
                        .channel_text_selected));
            }
            channelTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < channelContent.getChildCount(); i++) {
                        TextView localView = (TextView) channelContent.getChildAt(i);
                        if ( localView != v) {
                            localView.setSelected(false);
                            localView.setTextAppearance(MainActivity.this, R.style
                                    .top_category_scroll_view_item_text);
                        } else {
                            localView.setSelected(true);
                            localView.setTextColor(getResources().getColor(R.color
                                    .channel_text_selected));
                            localView.setTextSize(getResources().getDimension(R.dimen
                                    .channel_text_selected));

                        }
                    }
                }
            });
            channelContent.addView(channelTextView);

        }

    }
}
