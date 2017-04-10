package com.queen.rxjava2withretrofitdemo.mvpWithRxjavaDemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.queen.rxjava2withretrofitdemo.R;
import com.queen.rxjava2withretrofitdemo.mvpDemo.presenter.DoubanMovieDetailPresenter;
import com.queen.rxjava2withretrofitdemo.mvpDemo.view.DoubanMovieDetailFragment;
import com.queen.rxjava2withretrofitdemo.mvpDemo.view.DoubanMovieFragment;
import com.queen.rxjava2withretrofitdemo.mvpWithRxjavaDemo.presenter.RxDoubanMovieDetailPresenter;
import com.queen.rxjava2withretrofitdemo.mvpWithRxjavaDemo.presenter.RxjavaDoubanMoviePresenter;

/**
 * Created by liukun on 2017/4/6.
 */

public class RxDoubanMovieActivity extends AppCompatActivity{

    public static final String TAG = RxDoubanMovieActivity.class.getSimpleName();

    private RxjavaDoubanMoviePresenter mDoubanMoviePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_douban_movie);

        DoubanMovieFragment fragment = (DoubanMovieFragment) getSupportFragmentManager().findFragmentById(R.id.douban_movie_content);

        if (fragment == null) {
            fragment = DoubanMovieFragment.newInstance();
        }

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.douban_movie_content, fragment)
                .commit();

        mDoubanMoviePresenter = new RxjavaDoubanMoviePresenter(fragment);
    }

    public void transToMovieDetail(String movieId){

        DoubanMovieDetailFragment movieDetailFragment = DoubanMovieDetailFragment.newInstance(movieId);
        RxDoubanMovieDetailPresenter movieDetailPresenter = new RxDoubanMovieDetailPresenter(movieDetailFragment);

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(DoubanMovieFragment.TAG)
                .replace(R.id.douban_movie_content, movieDetailFragment)
                .commit();
    }
}
