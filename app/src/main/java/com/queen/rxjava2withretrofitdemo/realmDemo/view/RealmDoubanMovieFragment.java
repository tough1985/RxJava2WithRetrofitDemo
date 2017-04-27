package com.queen.rxjava2withretrofitdemo.realmDemo.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.queen.mvplib.MvpFragment;
import com.queen.rxjava2withretrofitdemo.R;
import com.queen.rxjava2withretrofitdemo.realmDemo.activity.RealmDoubanActivity;
import com.queen.rxjava2withretrofitdemo.realmDemo.adapter.RealmDoubanMovieAdapter;
import com.queen.rxjava2withretrofitdemo.realmDemo.contract.RealmDoubanMovieContract;
import com.queen.rxjava2withretrofitdemo.realmDemo.presenter.RealmDoubanMoviePresenter;
import com.queen.rxjava2withretrofitdemo.RealmEntityTest.RealmDoubanMovieSubject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.realm.RealmDoubanAvatarRealmProxy;

/**
 * Created by liukun on 2017/4/10.
 */

public class RealmDoubanMovieFragment extends MvpFragment<RealmDoubanMovieContract.View, RealmDoubanMovieContract
        .Presenter<RealmDoubanMovieContract.View>>
        implements
        RealmDoubanMovieContract.View {

    public static final String TAG = RealmDoubanMovieFragment.class.getSimpleName();

    @BindView(R.id.fragment_douban_movie_loading_PB)
    ProgressBar fragmentDoubanMovieLoadingPB;
    @BindView(R.id.fragment_douban_movie_LV)
    ListView fragmentDoubanMovieLV;
    @BindView(R.id.fragment_douban_movie_null_TV)
    TextView fragmentDoubanMovieNullTV;
    Unbinder unbinder;

//    private RealmDoubanMovieContract.Presenter mPresenter;

    private RealmDoubanMovieAdapter mAdapter;

    private RealmDoubanMovieAdapter.DoubanMovieItemClickListener mItemClickListener;

    public RealmDoubanMovieFragment() {
    }

    public static RealmDoubanMovieFragment newInstance() {
        return new RealmDoubanMovieFragment();
    }

    @Override
    public RealmDoubanMovieContract.Presenter<RealmDoubanMovieContract.View> createPresenter() {
        return new RealmDoubanMoviePresenter();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mItemClickListener = new RealmDoubanMovieAdapter.DoubanMovieItemClickListener() {
            @Override
            public void onDoubanMovieItemClick(RealmDoubanMovieSubject doubanMovieSubject) {
                ((RealmDoubanActivity)getActivity()).transToMovieDetail(doubanMovieSubject);
            }
        };

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
    public boolean isAlived() {
        return this.isAdded();
    }

//    @Override
//    public void setPresenter(RealmDoubanMovieContract.Presenter presenter) {
//        mPresenter = presenter;
//    }

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
    public void setMovies(ArrayList<RealmDoubanMovieSubject> doubanMovieSubjects) {
        mAdapter = new RealmDoubanMovieAdapter(getContext(), doubanMovieSubjects, mItemClickListener);
        fragmentDoubanMovieLV.setAdapter(mAdapter);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
