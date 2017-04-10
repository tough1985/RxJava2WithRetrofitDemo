package com.queen.rxjava2withretrofitdemo.mvpDemo.model;

import com.queen.rxjava2withretrofitdemo.entity.DoubanMovieDetail;
import com.queen.rxjava2withretrofitdemo.entity.DoubanMovieResult;
import com.queen.rxjava2withretrofitdemo.mvpDemo.service.DoubanMovieService;
import com.queen.rxjava2withretrofitdemo.simpleRetrofit.SignInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by liukun on 2017/4/6.
 */

public class DoubanMovieModel {
    public static final String BASE_URL = "https://api.douban.com/v2/";

    private Retrofit mRetrofit;
    private OkHttpClient client;

    private DoubanMovieService mDoubanMovieService;

    private DoubanMovieModel() {

        client = new OkHttpClient.Builder().addInterceptor(new SignInterceptor())
                .connectTimeout(15, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mDoubanMovieService = mRetrofit.create(DoubanMovieService.class);
    }

    private static DoubanMovieModel instance;

    public static DoubanMovieModel getInstance() {

        if (instance == null) {
            synchronized (DoubanMovieModel.class) {
                instance = new DoubanMovieModel();
            }
        }
        return instance;

    }

    public void getMovieInTheaters(String city, Callback<DoubanMovieResult> call){
        mDoubanMovieService.getMovieInTheaters(city).enqueue(call);
    }

    public void getMovieDetail(String movieId, Callback<DoubanMovieDetail> call){
        mDoubanMovieService.getMovieDetail(movieId).enqueue(call);
    }
}
