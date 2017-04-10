package com.queen.rxjava2withretrofitdemo.mvpDemo.contract;

import com.queen.rxjava2withretrofitdemo.base.BasePresenter;
import com.queen.rxjava2withretrofitdemo.base.BaseView;
import com.queen.rxjava2withretrofitdemo.entity.DoubanMovieSubject;

import java.util.ArrayList;

/**
 * Created by liukun on 2017/4/6.
 */
public interface DoubanMovieContract {

    interface View extends BaseView<Presenter>{

        void setMovies(ArrayList<DoubanMovieSubject> doubanMovieSubjects);

        void showError(String errMes);

        void showLoading();

        void showNull();

        void dismissLoading();

    }

    interface Presenter extends BasePresenter{

    }
}
