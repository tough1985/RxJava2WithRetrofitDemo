package com.queen.rxjava2withretrofitdemo.base;

/**
 * Created by liukun on 2017/4/6.
 */

public interface BaseView<T> {
    void setPresenter(T presenter);

    boolean isAlived();

    void showError(String errMes);

    void showLoading();

    void showNull();

    void dismissLoading();
}
