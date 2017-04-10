package com.queen.rxjava2withretrofitdemo.mvpWithRxjavaDemo.presenter;

import com.queen.rxjava2withretrofitdemo.App;
import com.queen.rxjava2withretrofitdemo.entity.DoubanAvatar;
import com.queen.rxjava2withretrofitdemo.entity.DoubanMovieDetail;
import com.queen.rxjava2withretrofitdemo.entity.DoubanMovieResult;
import com.queen.rxjava2withretrofitdemo.entity.DoubanMovieSubject;
import com.queen.rxjava2withretrofitdemo.entity.DoubanRating;
import com.queen.rxjava2withretrofitdemo.mvpDemo.contract.DoubanMovieContract;
import com.queen.rxjava2withretrofitdemo.mvpWithRxjavaDemo.model.RxjavaDoubanMovieModel;
import com.queen.rxjava2withretrofitdemo.mvpWithRxjavaDemo.observer.LoadingObserver;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by liukun on 2017/4/6.
 */

public class RxjavaDoubanMoviePresenter implements DoubanMovieContract.Presenter {

    private DoubanMovieContract.View mView;

    public RxjavaDoubanMoviePresenter(DoubanMovieContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

        Observer observer = new LoadingObserver<DoubanMovieResult>(mView, new LoadingObserver
                .ObserverOnNextListener<DoubanMovieResult>() {
            @Override
            public void observerOnNext(DoubanMovieResult value) {
                //如果返回数据为空，显示空态
                if (value == null || value.getSubjects() == null || value.getSubjects().size() == 0) {
                    mView.showNull();
                } else {
                    mView.setMovies(value.getSubjects());

                }
            }
        });


        RxjavaDoubanMovieModel.getInstance().getMovieInTheaters("上海", observer);
    }
}
