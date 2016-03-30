package com.for_futrue.zxg.znews.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.for_futrue.zxg.znews.presenter.Presenter;
import com.for_futrue.zxg.znews.view.Ui;

/**
 * Created by zxg on 2016/3/30.
 */
public abstract class BaseFragment<T extends Presenter<U>,U extends Ui> extends Fragment{
    private T mPresenter;
    abstract T createPresenter();
    abstract U getUi();
    protected BaseFragment(){
        mPresenter = createPresenter();
    }
    /**
     * Presenter will be available after onActivityCreated().
     *
     * @return The presenter associated with this fragment.
     */
    public T getmPresenter(){
        return mPresenter;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.onUiReady(getUi());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.onUiDestroy(getUi());
    }
}
