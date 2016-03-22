package com.for_futrue.zxg.znews.presenter;

import com.for_futrue.zxg.znews.view.Ui;

/**
 * Created by zxg on 2016/3/21.
 */
public abstract class Presenter<U extends Ui> {
    private U mUi;
    public void onUiReady(U ui){
        mUi = ui;
    }
    public final void onUiDestroy(U ui){
        onUiUnReady(ui);
        mUi = null;
    }
    public void onUiUnReady(U ui){

    }
    public U getUi(){
        return mUi;
    }
}
