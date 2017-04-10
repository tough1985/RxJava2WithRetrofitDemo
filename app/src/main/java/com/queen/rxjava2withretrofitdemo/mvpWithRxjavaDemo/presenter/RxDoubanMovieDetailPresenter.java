package com.queen.rxjava2withretrofitdemo.mvpWithRxjavaDemo.presenter;

import com.queen.rxjava2withretrofitdemo.entity.DoubanMovieDetail;
import com.queen.rxjava2withretrofitdemo.mvpDemo.contract.DoubanMovieDetailContract;
import com.queen.rxjava2withretrofitdemo.mvpWithRxjavaDemo.model.RxjavaDoubanMovieModel;
import com.queen.rxjava2withretrofitdemo.mvpWithRxjavaDemo.observer.LoadingObserver;

import io.reactivex.Observer;

/**
 * Created by liukun on 2017/4/6.
 */

public class RxDoubanMovieDetailPresenter implements DoubanMovieDetailContract.Presenter {

    private DoubanMovieDetailContract.View mView;

    public RxDoubanMovieDetailPresenter(DoubanMovieDetailContract.View view) {
        this.mView = view;

        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getMovie(String movieId) {


        Observer observer = new LoadingObserver<DoubanMovieDetail>(mView, new LoadingObserver
                .ObserverOnNextListener<DoubanMovieDetail>() {
            @Override
            public void observerOnNext(DoubanMovieDetail value) {
                //如果返回数据为空，显示空态
                if (value == null) {
                    mView.showNull();
                } else {
                    mView.setMovie(value);
                }
            }
        });

        RxjavaDoubanMovieModel.getInstance().getMovieDetail(movieId,observer);
    }
}