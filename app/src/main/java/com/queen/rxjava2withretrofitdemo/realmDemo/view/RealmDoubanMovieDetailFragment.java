package com.queen.rxjava2withretrofitdemo.realmDemo.view;

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
import com.queen.rxjava2withretrofitdemo.realmDemo.contract.RealmDoubanMovieDetailContract;
import com.queen.rxjava2withretrofitdemo.realmEntity.RealmDoubanMovieDetail;
import com.queen.rxjava2withretrofitdemo.realmEntity.RealmDoubanMovieSubject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by liukun on 2017/4/11.
 */

public class RealmDoubanMovieDetailFragment extends Fragment implements RealmDoubanMovieDetailContract.View {

    public static final String TAG = RealmDoubanMovieDetailFragment.class.getSimpleName();

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

    private RealmDoubanMovieDetailContract.Presenter mPresenter;

    private RealmDoubanMovieSubject subject;

    public static RealmDoubanMovieDetailFragment newInstance(RealmDoubanMovieSubject subject){
        RealmDoubanMovieDetailFragment fragment = new RealmDoubanMovieDetailFragment();

        Bundle args = new Bundle();
//        args.putParcelable("subject", Parcels.wrap(subject));
        args.putParcelable("subject", subject);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        subject = Parcels.unwrap(getArguments().getParcelable("subject"));
        subject = getArguments().getParcelable("subject");
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

        mPresenter.getMovie(subject.getId());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public boolean isAlived() {
        return this.isAdded();
    }

    @Override
    public void setPresenter(RealmDoubanMovieDetailContract.Presenter presenter) {
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
    public void setMovie(RealmDoubanMovieDetail doubanMovieDetail) {
        fragmentDoubanMovieDetailDirectorTV.setText("导演：" + doubanMovieDetail.getDirectorNames());
        fragmentDoubanMovieDetailCastTV.setText("主演：" + doubanMovieDetail.getCastsNames());
        fragmentDoubanMovieDetailTitleTV.setText(doubanMovieDetail.getTitle());
        fragmentDoubanMovieDetailYearTV.setText("上映时间：" + doubanMovieDetail.getYear());
        fragmentDoubanMovieDetailSummaryTV.setText("简介：" + doubanMovieDetail.getSummary());
        fragmentDoubanMovieDetailCountriesTV.setText("国家：" + doubanMovieDetail.getContriesString());
        fragmentDoubanMovieDetailGenresTV.setText("类型：" + doubanMovieDetail.getGenresStr());

        Glide.with(getContext()).load(doubanMovieDetail.getImages().getLarge()).into(fragmentDoubanMovieDetailImageIV);
    }
}
