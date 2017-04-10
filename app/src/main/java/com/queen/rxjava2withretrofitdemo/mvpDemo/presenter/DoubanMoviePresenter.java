package com.queen.rxjava2withretrofitdemo.mvpDemo.presenter;

import android.support.annotation.NonNull;

import com.queen.rxjava2withretrofitdemo.entity.DoubanMovieResult;
import com.queen.rxjava2withretrofitdemo.mvpDemo.contract.DoubanMovieContract;
import com.queen.rxjava2withretrofitdemo.mvpDemo.model.DoubanMovieModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by liukun on 2017/4/6.
 */

public class DoubanMoviePresenter implements DoubanMovieContract.Presenter {

    private final DoubanMovieContract.View mDoubanMovieView;

    public DoubanMoviePresenter(@NonNull DoubanMovieContract.View doubanMovieView) {
        mDoubanMovieView = doubanMovieView;
        mDoubanMovieView.setPresenter(this);
    }

    @Override
    public void start() {
        mDoubanMovieView.showLoading();

        DoubanMovieModel.getInstance().getMovieInTheaters("上海", new Callback<DoubanMovieResult>() {
            @Override
            public void onResponse(Call<DoubanMovieResult> call, Response<DoubanMovieResult> response) {
                mDoubanMovieView.dismissLoading();

                //如果返回数据为空，显示空态
                if (response.body() == null || response.body().getSubjects() == null || response.body().getSubjects().size() == 0) {
                    mDoubanMovieView.showNull();
                } else {
                    mDoubanMovieView.setMovies(response.body().getSubjects());
                }
            }

            @Override
            public void onFailure(Call<DoubanMovieResult> call, Throwable t) {
                mDoubanMovieView.dismissLoading();
                mDoubanMovieView.showError(t.getMessage());
            }
        });
    }
}
