package com.queen.mvplib;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.queen.mvplib.BaseMvp.MvpPresenter;
import com.queen.mvplib.BaseMvp.MvpView;
import com.queen.mvplib.delegate.MvpDelegate;
import com.queen.mvplib.delegate.MvpDelegateCallback;

/**
 * Created by liukun on 2017/4/18.
 */

public abstract class MvpActivity<V extends MvpView, P extends MvpPresenter<V>> extends AppCompatActivity implements MvpView,
        MvpDelegateCallback<V, P> {

    protected MvpDelegate<V, P> mMvpDelegate;
    protected P mPresenter;

    public abstract P createaPresenter();

    protected MvpDelegate<V, P> getMvpDelegate(){
        if (mMvpDelegate == null) {
            mMvpDelegate = new MvpDelegate<>(this);
        }
        return mMvpDelegate;
    }

    @Override
    public P getPresenter() {
        return mPresenter;
    }

    @Override
    public void setPresenter(P presenter) {
        mPresenter = presenter;
    }

    @Override
    public V getMvpView() {
        return (V) this;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMvpDelegate().onViewCreated();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getMvpDelegate().onDestroyView();
    }
}
