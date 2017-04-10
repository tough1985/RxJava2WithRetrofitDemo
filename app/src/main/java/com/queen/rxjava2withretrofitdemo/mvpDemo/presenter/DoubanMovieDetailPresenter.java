package com.queen.rxjava2withretrofitdemo.mvpDemo.presenter;

import com.queen.rxjava2withretrofitdemo.entity.DoubanMovieDetail;
import com.queen.rxjava2withretrofitdemo.mvpDemo.contract.DoubanMovieDetailContract;
import com.queen.rxjava2withretrofitdemo.mvpDemo.model.DoubanMovieModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by liukun on 2017/4/6.
 */

public class DoubanMovieDetailPresenter implements DoubanMovieDetailContract.Presenter {

    private DoubanMovieDetailContract.View mView;

    public DoubanMovieDetailPresenter(DoubanMovieDetailContract.View view) {
        this.mView = view;

        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getMovie(String movieId) {
        mView.showLoading();
        DoubanMovieModel.getInstance().getMovieDetail(movieId, new Callback<DoubanMovieDetail>() {
            @Override
            public void onResponse(Call<DoubanMovieDetail> call, Response<DoubanMovieDetail> response) {
                mView.dismissLoading();
                //如果返回数据为空，显示空态
                if (response.body() == null) {
                    mView.showNull();
                } else {
                    mView.setMovie(response.body());
                }
            }

            @Override
            public void onFailure(Call<DoubanMovieDetail> call, Throwable t) {
                mView.dismissLoading();
                mView.showError(t.getMessage());
            }
        });
    }
}
