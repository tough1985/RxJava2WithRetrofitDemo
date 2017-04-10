package com.queen.rxjava2withretrofitdemo.greenDaoDemo.service;

import com.queen.rxjava2withretrofitdemo.entity.DoubanMovieDetail;
import com.queen.rxjava2withretrofitdemo.entity.DoubanMovieResult;
import com.queen.rxjava2withretrofitdemo.greenDaoEntity.GreenDoubanMovieDetail;
import com.queen.rxjava2withretrofitdemo.greenDaoEntity.GreenDoubanMovieResult;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by liukun on 2017/4/10.
 */

public interface GreenDoubanMovieService {

    @GET("movie/in_theaters")
    Observable<GreenDoubanMovieResult> getMovieInTheaters(@Query("city") String city);


    @GET("movie/subject/{id}")
    Observable<GreenDoubanMovieDetail> getMovieDetail(@Path("id") String movieId);
}
