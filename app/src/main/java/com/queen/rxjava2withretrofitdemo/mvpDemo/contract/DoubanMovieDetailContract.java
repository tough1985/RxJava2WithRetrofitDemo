package com.queen.rxjava2withretrofitdemo.mvpDemo.contract;

import com.queen.rxjava2withretrofitdemo.base.BasePresenter;
import com.queen.rxjava2withretrofitdemo.base.BaseView;
import com.queen.rxjava2withretrofitdemo.entity.DoubanMovieDetail;
import com.queen.rxjava2withretrofitdemo.entity.DoubanMovieSubject;

import java.util.ArrayList;

/**
 * Created by liukun on 2017/4/6.
 */

public interface DoubanMovieDetailContract {

    interface View extends BaseView<DoubanMovieDetailContract.Presenter> {

        void setMovie(DoubanMovieDetail doubanMovieDetail);

    }

    interface Presenter extends BasePresenter {
        void getMovie(String movieId);
    }
}
