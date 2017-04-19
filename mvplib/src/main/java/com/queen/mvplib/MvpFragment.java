package com.queen.mvplib;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import com.queen.mvplib.BaseMvp.MvpPresenter;
import com.queen.mvplib.BaseMvp.MvpView;
import com.queen.mvplib.delegate.MvpDelegate;
import com.queen.mvplib.delegate.MvpDelegateCallback;

/**
 * Created by liukun on 2017/4/18.
 */

public abstract class MvpFragment<V extends MvpView, P extends MvpPresenter<V>>
        extends Fragment
        implements MvpDelegateCallback<V, P>, MvpView{

    protected MvpDelegate<V, P> mMvpDelegate;
    protected P mPresenter;

    public abstract P createPresenter();

    protected MvpDelegate<V, P> getMvpDelegate(){
        if (mMvpDelegate == null) {
            mMvpDelegate = new MvpDelegate<>(this);
        }
        return mMvpDelegate;
    }

    @Override
    public P getPresenter() {
        Log.e("MvpFragment", "getPresenter");
        return mPresenter;
    }

    @Override
    public void setPresenter(P presenter) {
        Log.e("MvpFragment", "getPresenter");
        mPresenter = presenter;
    }

    @Override
    public V getMvpView() {
        return (V) this;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getMvpDelegate().onViewCreated();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getMvpDelegate().onDestroyView();
    }
}
