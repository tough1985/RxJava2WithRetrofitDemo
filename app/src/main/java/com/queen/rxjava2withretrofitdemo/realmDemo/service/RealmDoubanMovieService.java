package com.queen.rxjava2withretrofitdemo.realmDemo.service;

import com.queen.rxjava2withretrofitdemo.realmEntity.RealmDoubanMovieDetail;
import com.queen.rxjava2withretrofitdemo.realmEntity.RealmDoubanMovieResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by liukun on 2017/4/10.
 */

public interface RealmDoubanMovieService {

    @GET("movie/in_theaters")
    Observable<RealmDoubanMovieResult> getMovieInTheaters(@Query("city") String city);


    @GET("movie/subject/{id}")
    Observable<RealmDoubanMovieDetail> getMovieDetail(@Path("id") String movieId);
}
