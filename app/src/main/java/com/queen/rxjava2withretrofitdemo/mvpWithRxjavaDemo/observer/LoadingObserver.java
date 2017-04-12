package com.queen.rxjava2withretrofitdemo.mvpWithRxjavaDemo.observer;

import com.queen.rxjava2withretrofitdemo.base.BaseView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by liukun on 2017/4/6.
 */

public class LoadingObserver<T> implements Observer<T> {

    private BaseView mBaseView;
    private ObserverOnNextListener<T> mOnNextListener;

    public LoadingObserver(BaseView mBaseView, ObserverOnNextListener<T> mOnNextListener) {
        this.mBaseView = mBaseView;
        this.mOnNextListener = mOnNextListener;
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (mBaseView != null && mBaseView.isAlived()) {
            mBaseView.showLoading();
        }
    }

    @Override
    public void onNext(T value) {
        if (mOnNextListener != null) {
            mOnNextListener.observerOnNext(value);
        }
    }

    @Override
    public void onError(Throwable e) {
        if (mBaseView != null && mBaseView.isAlived()) {
            mBaseView.showError(e.getMessage());
        }
    }

    @Override
    public void onComplete() {
        if (mBaseView != null && mBaseView.isAlived()) {
            mBaseView.dismissLoading();
        }
    }

    public interface ObserverOnNextListener<T>{
        void observerOnNext(T value);
    }
}
