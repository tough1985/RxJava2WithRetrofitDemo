package com.queen.rxjava2withretrofitdemo.mvpWithRxjavaDemo.service;

import com.queen.rxjava2withretrofitdemo.entity.DoubanMovieDetail;
import com.queen.rxjava2withretrofitdemo.entity.DoubanMovieResult;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by liukun on 2017/4/6.
 */

public interface RxjavaDoubanMovieService {

    @GET("movie/in_theaters")
    Observable<DoubanMovieResult> getMovieInTheaters(@Query("city") String city);


    @GET("movie/subject/{id}")
    Observable<DoubanMovieDetail> getMovieDetail(@Path("id") String movieId);
}
