package com.queen.rxjava2withretrofitdemo.greenDaoDemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.queen.rxjava2withretrofitdemo.R;
import com.queen.rxjava2withretrofitdemo.greenDaoDemo.presenter.GreenDaoDoubanPresenter;
import com.queen.rxjava2withretrofitdemo.greenDaoDemo.view.GreenDoubanMovieFragment;
import com.queen.rxjava2withretrofitdemo.mvpDemo.view.DoubanMovieDetailFragment;
import com.queen.rxjava2withretrofitdemo.mvpDemo.view.DoubanMovieFragment;
import com.queen.rxjava2withretrofitdemo.mvpWithRxjavaDemo.presenter.RxDoubanMovieDetailPresenter;

/**
 * Created by liukun on 2017/4/10.
 */

public class GreenDaoDoubanActivity extends AppCompatActivity {

    private GreenDaoDoubanPresenter mDoubanMoviePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_douban_movie);

        GreenDoubanMovieFragment fragment = (GreenDoubanMovieFragment) getSupportFragmentManager().findFragmentById(R.id
                .douban_movie_content);

        if (fragment == null) {
            fragment = GreenDoubanMovieFragment.newInstance();
        }

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.douban_movie_content, fragment)
                .commit();

        mDoubanMoviePresenter = new GreenDaoDoubanPresenter(fragment);
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
