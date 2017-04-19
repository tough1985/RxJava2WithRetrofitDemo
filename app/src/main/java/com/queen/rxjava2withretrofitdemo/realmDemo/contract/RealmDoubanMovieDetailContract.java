package com.queen.rxjava2withretrofitdemo.realmDemo.contract;

import com.queen.mvplib.BaseMvp.MvpPresenter;
import com.queen.mvplib.BaseMvp.MvpView;
import com.queen.rxjava2withretrofitdemo.base.BasePresenter;
import com.queen.rxjava2withretrofitdemo.base.BaseView;
import com.queen.rxjava2withretrofitdemo.realmEntity.RealmDoubanMovieDetail;

/**
 * Created by liukun on 2017/4/11.
 */

public interface RealmDoubanMovieDetailContract {

    interface View extends MvpView {

        void setMovie(RealmDoubanMovieDetail doubanMovieDetail);

    }

    interface Presenter<V extends MvpView> extends MvpPresenter<V> {
        void getMovie(String movieId);

    }
}
