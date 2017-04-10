package com.queen.rxjava2withretrofitdemo.mvpDemo.view;

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
import com.queen.rxjava2withretrofitdemo.entity.DoubanMovieSubject;
import com.queen.rxjava2withretrofitdemo.mvpDemo.activity.DoubanMovieActivity;
import com.queen.rxjava2withretrofitdemo.mvpDemo.adapter.DoubanMovieAdapter;
import com.queen.rxjava2withretrofitdemo.mvpDemo.contract.DoubanMovieContract;
import com.queen.rxjava2withretrofitdemo.mvpWithRxjavaDemo.activity.RxDoubanMovieActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by liukun on 2017/4/6.
 */
public class DoubanMovieFragment extends Fragment implements DoubanMovieContract.View {

    public static final String TAG = DoubanMovieFragment.class.getSimpleName();

    @BindView(R.id.fragment_douban_movie_loading_PB)
    ProgressBar fragmentDoubanMovieLoadingPB;
    @BindView(R.id.fragment_douban_movie_LV)
    ListView fragmentDoubanMovieLV;
    @BindView(R.id.fragment_douban_movie_null_TV)
    TextView fragmentDoubanMovieNullTV;
    Unbinder unbinder;

    private DoubanMovieContract.Presenter mPresenter;

    private DoubanMovieAdapter mAdapter;

    private DoubanMovieAdapter.DoubanMovieItemClickListener itemClickListener;

    public DoubanMovieFragment() {
    }

    public static DoubanMovieFragment newInstance() {
        return new DoubanMovieFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemClickListener = new DoubanMovieAdapter
                .DoubanMovieItemClickListener(){
            @Override
            public void onDoubanMovieItemClick(DoubanMovieSubject doubanMovieSubject) {

                if (getActivity() instanceof DoubanMovieActivity) {
                    ((DoubanMovieActivity)getActivity()).transToMovieDetail(doubanMovieSubject.getId());
                } else if (getActivity() instanceof RxDoubanMovieActivity) {
                    ((RxDoubanMovieActivity)getActivity()).transToMovieDetail(doubanMovieSubject.getId());
                }

            }
        };
    }

    @Override
    public void setPresenter(DoubanMovieContract.Presenter presenter) {
        mPresenter = presenter;
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
    public void setMovies(ArrayList<DoubanMovieSubject> doubanMovieSubjects) {

        mAdapter = new DoubanMovieAdapter(getContext(), doubanMovieSubjects, itemClickListener);
        fragmentDoubanMovieLV.setAdapter(mAdapter);

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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
