package com.queen.mvplib.BaseMvp;

import android.support.annotation.UiThread;

/**
 * Created by liukun on 2017/4/18.
 */

public interface MvpPresenter<V extends MvpView> {

    void start();
    /**
     * 为Presenter添加View
     * @param view
     */
    @UiThread
    void attachView(V view);

    /**
     * 解除Presenter与View的绑定
     */
    @UiThread
    void detachView();
}
