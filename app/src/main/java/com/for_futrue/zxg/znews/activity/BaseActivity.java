package com.for_futrue.zxg.znews.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.for_futrue.zxg.znews.presenter.Presenter;
import com.for_futrue.zxg.znews.view.Ui;

/**
 * Created by zxg on 2016/3/21.
 */
public abstract  class BaseActivity<T extends Presenter<U>,U extends Ui> extends FragmentActivity {
    private T mPresetner;
    abstract T createPresenter();
    abstract U getUi();

    protected BaseActivity(){
        mPresetner = createPresenter();
    }
    public T getPresenter(){
        return mPresetner;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresetner.onUiReady(getUi());
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresetner.onUiUnReady(getUi());
    }
}
