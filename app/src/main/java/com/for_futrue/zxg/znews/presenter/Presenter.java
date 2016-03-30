package com.for_futrue.zxg.znews.presenter;

import com.for_futrue.zxg.znews.view.Ui;

/**
 * Created by zxg on 2016/3/21.
 * Base class for Presenters.
 */
public abstract class Presenter<U extends Ui> {
    private U mUi;

    /**
     * Called after UI view has been created. That is when fragment.onActivityCreated().
     *
     * @param ui The Ui implementation that is now ready to be used.
     */
    public void onUiReady(U ui){
        mUi = ui;
    }

    /**
     * Called when UI view is destoryed in fragment.onDestoryView()
     * @param ui
     */
    public final void onUiDestroy(U ui){
        onUiUnReady(ui);
        mUi = null;
    }

    /**
     * To be overriden by Presenter implementations.Called when the fragment is being
     * destoryed but before ui is set to null
     * @param ui
     */
    public void onUiUnReady(U ui){

    }
    public U getUi(){
        return mUi;
    }
}
