package com.for_futrue.zxg.znews.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.for_futrue.zxg.znews.R;
import com.for_futrue.zxg.znews.activity.BaseActivity;
import com.for_futrue.zxg.znews.presenter.ChannelPresenter;
import com.for_futrue.zxg.znews.view.ChannelFragmentUi;

/**
 * Created by zxg on 2017/1/3.
 */
public class ChannelFragment extends BaseFragment<ChannelPresenter,ChannelFragmentUi> implements ChannelFragmentUi{



    @Override
    ChannelPresenter createPresenter() {
        return new ChannelPresenter();
    }

    @Override
    ChannelFragmentUi getUi() {
        return this;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        Log.i("zxg33","oncreateview");
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.channel_fragment,null);
        return view;
    }
}
