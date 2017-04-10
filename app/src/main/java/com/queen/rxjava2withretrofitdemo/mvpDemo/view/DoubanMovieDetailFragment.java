package com.queen.rxjava2withretrofitdemo.mvpDemo.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.queen.rxjava2withretrofitdemo.R;
import com.queen.rxjava2withretrofitdemo.entity.DoubanMovieDetail;
import com.queen.rxjava2withretrofitdemo.entity.DoubanMovieSubject;
import com.queen.rxjava2withretrofitdemo.mvpDemo.contract.DoubanMovieDetailContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by liukun on 2017/4/6.
 */

public class DoubanMovieDetailFragment extends Fragment implements DoubanMovieDetailContract.View {

    public static final String TAG = DoubanMovieDetailFragment.class.getSimpleName();

    public static final String MOVIE_ID = "MovieId";
    @BindView(R.id.fragment_douban_movie_detail_loading_PB)
    ProgressBar fragmentDoubanMovieDetailLoadingPB;
    @BindView(R.id.fragment_douban_movie_detail_null_TV)
    TextView fragmentDoubanMovieDetailNullTV;
    @BindView(R.id.fragment_douban_movie_detail_image_IV)
    ImageView fragmentDoubanMovieDetailImageIV;
    @BindView(R.id.fragment_douban_movie_detail_title_TV)
    TextView fragmentDoubanMovieDetailTitleTV;
    @BindView(R.id.fragment_douban_movie_detail_genres_TV)
    TextView fragmentDoubanMovieDetailGenresTV;
    @BindView(R.id.fragment_douban_movie_detail_countries_TV)
    TextView fragmentDoubanMovieDetailCountriesTV;
    @BindView(R.id.fragment_douban_movie_detail_year_TV)
    TextView fragmentDoubanMovieDetailYearTV;
    @BindView(R.id.fragment_douban_movie_detail_director_TV)
    TextView fragmentDoubanMovieDetailDirectorTV;
    @BindView(R.id.fragment_douban_movie_detail_cast_TV)
    TextView fragmentDoubanMovieDetailCastTV;
    @BindView(R.id.fragment_douban_movie_detail_summary_TV)
    TextView fragmentDoubanMovieDetailSummaryTV;
    Unbinder unbinder;

    private String movieId;

    private DoubanMovieDetailContract.Presenter mPresenter;

    public static DoubanMovieDetailFragment newInstance(String movieId) {
        DoubanMovieDetailFragment fragment = new DoubanMovieDetailFragment();

        Bundle args = new Bundle();
        args.putString(MOVIE_ID, movieId);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieId = getArguments().getString(MOVIE_ID);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_douban_movie_detail, container, false);

        unbinder = ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getMovie(movieId);
    }

    @Override
    public void setPresenter(DoubanMovieDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showError(String errMes) {
        Toast.makeText(getActivity(), errMes, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        fragmentDoubanMovieDetailLoadingPB.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNull() {
        fragmentDoubanMovieDetailNullTV.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissLoading() {
        fragmentDoubanMovieDetailLoadingPB.setVisibility(View.GONE);
    }

    @Override
    public void setMovie(DoubanMovieDetail doubanMovieSubject) {
        fragmentDoubanMovieDetailDirectorTV.setText("导演：" + doubanMovieSubject.getDirectorNames());
        fragmentDoubanMovieDetailCastTV.setText("主演：" + doubanMovieSubject.getCastsNames());
        fragmentDoubanMovieDetailTitleTV.setText(doubanMovieSubject.getTitle());
        fragmentDoubanMovieDetailYearTV.setText("上映时间：" + doubanMovieSubject.getYear());
        fragmentDoubanMovieDetailSummaryTV.setText("简介：" + doubanMovieSubject.getSummary());
        fragmentDoubanMovieDetailCountriesTV.setText("国家：" + doubanMovieSubject.getContriesString());
        fragmentDoubanMovieDetailGenresTV.setText("类型：" + doubanMovieSubject.getGenresStr());

        Glide.with(getContext()).load(doubanMovieSubject.getImages().getLarge()).into(fragmentDoubanMovieDetailImageIV);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
