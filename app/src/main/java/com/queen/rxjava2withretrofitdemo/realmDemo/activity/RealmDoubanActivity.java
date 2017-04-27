package com.queen.rxjava2withretrofitdemo.realmDemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.queen.rxjava2withretrofitdemo.R;
import com.queen.rxjava2withretrofitdemo.realmDemo.presenter.RealmDoubanMoviePresenter;
import com.queen.rxjava2withretrofitdemo.realmDemo.view.RealmDoubanMovieDetailFragment;
import com.queen.rxjava2withretrofitdemo.realmDemo.view.RealmDoubanMovieFragment;
import com.queen.rxjava2withretrofitdemo.RealmEntityTest.RealmDoubanMovieSubject;

/**
 * Created by liukun on 2017/4/10.
 */

public class RealmDoubanActivity extends AppCompatActivity {

    private RealmDoubanMoviePresenter mDoubanMoviePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_douban_movie);

        RealmDoubanMovieFragment fragment = (RealmDoubanMovieFragment) getSupportFragmentManager().findFragmentById(R.id
                .douban_movie_content);

        if (fragment == null) {
            fragment = RealmDoubanMovieFragment.newInstance();
        }

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.douban_movie_content, fragment)
                .commit();

//        mDoubanMoviePresenter = new RealmDoubanMoviePresenter(fragment);
    }

    public void transToMovieDetail(RealmDoubanMovieSubject subject){

        RealmDoubanMovieDetailFragment movieDetailFragment = RealmDoubanMovieDetailFragment.newInstance(subject);
//        RealmDoubanMovieDetailContract.Presenter movieDetailPresenter = new RealmDoubanMovieDetailPresenter(movieDetailFragment);

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(RealmDoubanMovieFragment.TAG)
                .replace(R.id.douban_movie_content, movieDetailFragment)
                .commit();
    }
}
