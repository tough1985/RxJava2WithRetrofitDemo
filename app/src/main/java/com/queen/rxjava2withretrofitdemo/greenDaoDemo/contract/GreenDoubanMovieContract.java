package com.queen.rxjava2withretrofitdemo.greenDaoDemo.contract;

import com.queen.rxjava2withretrofitdemo.base.BasePresenter;
import com.queen.rxjava2withretrofitdemo.base.BaseView;
import com.queen.rxjava2withretrofitdemo.entity.DoubanMovieSubject;
import com.queen.rxjava2withretrofitdemo.greenDaoEntity.GreenDoubanMovieSubject;
import com.queen.rxjava2withretrofitdemo.mvpDemo.contract.DoubanMovieContract;

import java.util.ArrayList;

/**
 * Created by liukun on 2017/4/10.
 */

public interface GreenDoubanMovieContract {
    interface View extends BaseView<GreenDoubanMovieContract.Presenter> {

        void setMovies(ArrayList<GreenDoubanMovieSubject> doubanMovieSubjects);


    }

    interface Presenter extends BasePresenter {

    }
}
