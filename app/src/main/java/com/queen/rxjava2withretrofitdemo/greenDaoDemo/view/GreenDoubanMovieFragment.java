package com.queen.rxjava2withretrofitdemo.greenDaoDemo.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.queen.rxjava2withretrofitdemo.R;
import com.queen.rxjava2withretrofitdemo.greenDaoDemo.adapter.GreenDoubanMovieAdapter;
import com.queen.rxjava2withretrofitdemo.greenDaoDemo.contract.GreenDoubanMovieContract;
import com.queen.rxjava2withretrofitdemo.greenDaoEntity.GreenDoubanMovieSubject;
import com.queen.rxjava2withretrofitdemo.mvpDemo.adapter.DoubanMovieAdapter;
import com.queen.rxjava2withretrofitdemo.mvpDemo.contract.DoubanMovieContract;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by liukun on 2017/4/10.
 */

public class GreenDoubanMovieFragment extends Fragment implements GreenDoubanMovieContract.View {

    public static final String TAG = GreenDoubanMovieFragment.class.getSimpleName();
    @BindView(R.id.fragment_douban_movie_loading_PB)
    ProgressBar fragmentDoubanMovieLoadingPB;
    @BindView(R.id.fragment_douban_movie_LV)
    ListView fragmentDoubanMovieLV;
    @BindView(R.id.fragment_douban_movie_null_TV)
    TextView fragmentDoubanMovieNullTV;
    Unbinder unbinder;

    private GreenDoubanMovieContract.Presenter mPresenter;

    private GreenDoubanMovieAdapter mAdapter;

    public GreenDoubanMovieFragment() {
    }

    public static GreenDoubanMovieFragment newInstance() {
        return new GreenDoubanMovieFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_douban_movie, container, false);

        unbinder = ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(GreenDoubanMovieContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showError(String errMes) {
        Toast.makeText(getActivity(), errMes, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        fragmentDoubanMovieLoadingPB.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNull() {
        fragmentDoubanMovieNullTV.setVisibility(View.VISIBLE);
        fragmentDoubanMovieLV.setVisibility(View.GONE);
    }

    @Override
    public void dismissLoading() {
        fragmentDoubanMovieLoadingPB.setVisibility(View.GONE);
    }

    @Override
    public void setMovies(ArrayList<GreenDoubanMovieSubject> doubanMovieSubjects) {
        mAdapter = new GreenDoubanMovieAdapter(getContext(), doubanMovieSubjects);
        fragmentDoubanMovieLV.setAdapter(mAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
