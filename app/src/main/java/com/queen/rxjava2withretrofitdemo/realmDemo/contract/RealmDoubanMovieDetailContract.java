package com.queen.rxjava2withretrofitdemo.realmDemo.contract;

import com.queen.rxjava2withretrofitdemo.base.BasePresenter;
import com.queen.rxjava2withretrofitdemo.base.BaseView;
import com.queen.rxjava2withretrofitdemo.realmEntity.RealmDoubanMovieDetail;

/**
 * Created by liukun on 2017/4/11.
 */

public interface RealmDoubanMovieDetailContract {

    interface View extends BaseView<RealmDoubanMovieDetailContract.Presenter> {

        void setMovie(RealmDoubanMovieDetail doubanMovieDetail);

    }

    interface Presenter extends BasePresenter {
        void getMovie(String movieId);

    }
}
