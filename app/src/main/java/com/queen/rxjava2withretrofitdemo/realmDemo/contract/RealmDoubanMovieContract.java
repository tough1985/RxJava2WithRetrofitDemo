package com.queen.rxjava2withretrofitdemo.realmDemo.contract;

import com.queen.rxjava2withretrofitdemo.base.BasePresenter;
import com.queen.rxjava2withretrofitdemo.base.BaseView;
import com.queen.rxjava2withretrofitdemo.greenDaoDemo.contract.GreenDoubanMovieContract;
import com.queen.rxjava2withretrofitdemo.greenDaoEntity.GreenDoubanMovieSubject;
import com.queen.rxjava2withretrofitdemo.realmEntity.RealmDoubanMovieSubject;

import java.util.ArrayList;

/**
 * Created by liukun on 2017/4/10.
 */

public interface RealmDoubanMovieContract {

    interface View extends BaseView<RealmDoubanMovieContract.Presenter> {

        void setMovies(ArrayList<RealmDoubanMovieSubject> doubanMovieSubjects);

    }

    interface Presenter extends BasePresenter {

    }
}
