package com.queen.rxjava2withretrofitdemo.realmDemo.contract;

import com.queen.mvplib.BaseMvp.MvpPresenter;
import com.queen.mvplib.BaseMvp.MvpView;
import com.queen.rxjava2withretrofitdemo.RealmEntityTest.RealmDoubanMovieSubject;

import java.util.ArrayList;

/**
 * Created by liukun on 2017/4/10.
 */

public interface RealmDoubanMovieContract {

    interface View extends MvpView {

        void setMovies(ArrayList<RealmDoubanMovieSubject> doubanMovieSubjects);

    }

    interface Presenter<V extends MvpView> extends MvpPresenter<V> {
    }
}
