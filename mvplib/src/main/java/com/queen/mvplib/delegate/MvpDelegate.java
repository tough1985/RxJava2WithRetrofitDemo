package com.queen.mvplib.delegate;

import android.util.Log;

import com.queen.mvplib.BaseMvp.MvpPresenter;
import com.queen.mvplib.BaseMvp.MvpView;

/**
 * Created by liukun on 2017/4/18.
 */

public class MvpDelegate<V extends MvpView, P extends MvpPresenter<V>> {

    private MvpDelegateCallback<V, P> mDelegateCallback;


    public MvpDelegate(MvpDelegateCallback<V, P> delegateCallback) {

        if (delegateCallback == null) {
            throw new NullPointerException("MvpDelegateCallback is null!");
        }

        this.mDelegateCallback = delegateCallback;
    }


    private P createaPresenter(){
        Log.e("MvpDelegate", "createaPresenter");
        P presenter = mDelegateCallback.createPresenter();

        if (presenter == null) {
            throw new NullPointerException("Presenter return from createaPresenter() is null");
        }

        return presenter;
    }

    private P getPresenter() {
        P presenter = mDelegateCallback.getPresenter();
        if (presenter == null) {
            throw new NullPointerException("Presenter returned from getPresenter() is null");
        }
        return presenter;
    }

    private V getMvpView() {
        V view = mDelegateCallback.getMvpView();
        if (view == null) {
            throw new NullPointerException("View returned from getMvpView() is null");
        }
        return view;
    }

    public void onViewCreated(){
        //创建Presenter
        P presenter = createaPresenter();

        //用过委托将Presenter设置给View
        mDelegateCallback.setPresenter(presenter);
        //将MvpView设置给Presenter
        presenter.attachView(getMvpView());

        Log.e("MvpDelegate", "onViewCreated: p == null ? " + (presenter == null));
    }

    public void onDestroyView(){
        P presenter = getPresenter();
        presenter.detachView();


    }

}
