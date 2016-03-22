package com.for_futrue.zxg.znews.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.for_futrue.zxg.znews.presenter.Presenter;
import com.for_futrue.zxg.znews.view.Ui;

/**
 * Created by zxg on 2016/3/21.
 */
public abstract  class BaseActivity<T extends Presenter<U>,U extends Ui> extends Activity{
    private T mPresetner;
    abstract T createPresenter();
    abstract U getUi();

    protected BaseActivity(){
        Log.i("zxg","baseactivity");
        mPresetner = createPresenter();
    }
    public T getPresenter(){
        return mPresetner;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresetner.onUiReady(getUi());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresetner.onUiUnReady(getUi());
    }
}
