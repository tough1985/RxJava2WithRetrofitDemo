package com.queen.rxjava2withretrofitdemo.greenDaoDemo.presenter;

import com.queen.rxjava2withretrofitdemo.greenDaoDemo.contract.GreenDoubanMovieContract;
import com.queen.rxjava2withretrofitdemo.greenDaoDemo.model.GreenDoubanMovieModel;
import com.queen.rxjava2withretrofitdemo.greenDaoEntity.GreenDoubanMovieResult;
import com.queen.rxjava2withretrofitdemo.greenDaoEntity.GreenDoubanMovieSubject;
import com.queen.rxjava2withretrofitdemo.mvpWithRxjavaDemo.observer.LoadingObserver;

import java.util.ArrayList;

import io.reactivex.Observer;

/**
 * Created by liukun on 2017/4/10.
 */

public class GreenDaoDoubanPresenter implements GreenDoubanMovieContract.Presenter {

    private GreenDoubanMovieContract.View mView;

    public GreenDaoDoubanPresenter(GreenDoubanMovieContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

        Observer observer = new LoadingObserver<GreenDoubanMovieResult>(mView, new LoadingObserver
                .ObserverOnNextListener<GreenDoubanMovieResult>() {
            @Override
            public void observerOnNext(GreenDoubanMovieResult value) {
                //如果返回数据为空，显示空态
                if (value == null || value.getSubjects() == null || value.getSubjects().size() == 0) {
                    mView.showNull();
                } else {
//                    mView.setMovies(value.getSubjects());

                    mView.setMovies(value.getSubjects());

                    ArrayList<GreenDoubanMovieSubject> subjects = value.getSubjects();
                    GreenDoubanMovieModel.getInstance().saveSubjects(subjects);

                }
            }
        });


        GreenDoubanMovieModel.getInstance().getMovieInTheaters("上海", observer);
    }
}
