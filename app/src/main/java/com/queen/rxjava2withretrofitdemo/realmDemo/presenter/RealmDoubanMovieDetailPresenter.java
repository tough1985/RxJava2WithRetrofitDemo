package com.queen.rxjava2withretrofitdemo.realmDemo.presenter;

import com.queen.rxjava2withretrofitdemo.mvpWithRxjavaDemo.observer.LoadingObserver;
import com.queen.rxjava2withretrofitdemo.realmDemo.contract.RealmDoubanMovieDetailContract;
import com.queen.rxjava2withretrofitdemo.realmDemo.model.RealmDoubanMovieModel;
import com.queen.rxjava2withretrofitdemo.realmEntity.RealmDoubanMovieDetail;

import io.reactivex.Observer;

/**
 * Created by liukun on 2017/4/11.
 */

public class RealmDoubanMovieDetailPresenter implements RealmDoubanMovieDetailContract.Presenter{

    private RealmDoubanMovieDetailContract.View mView;

    public RealmDoubanMovieDetailPresenter(RealmDoubanMovieDetailContract.View mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getMovie(String movieId) {
        Observer observer = new LoadingObserver<RealmDoubanMovieDetail>(mView, new LoadingObserver
                .ObserverOnNextListener<RealmDoubanMovieDetail>() {
            @Override
            public void observerOnNext(RealmDoubanMovieDetail value) {
                //如果返回数据为空，显示空态
                if (value == null) {
                    mView.showNull();
                } else {
                    mView.setMovie(value);
                }
            }
        });

        RealmDoubanMovieModel.getInstance().getMovieDetail(movieId, observer);
    }
}
