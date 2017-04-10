package com.queen.rxjava2withretrofitdemo.mvpWithRxjavaDemo.model;

import com.queen.rxjava2withretrofitdemo.entity.DoubanMovieDetail;
import com.queen.rxjava2withretrofitdemo.entity.DoubanMovieResult;
import com.queen.rxjava2withretrofitdemo.mvpDemo.model.DoubanMovieModel;
import com.queen.rxjava2withretrofitdemo.mvpDemo.service.DoubanMovieService;
import com.queen.rxjava2withretrofitdemo.mvpWithRxjavaDemo.service.RxjavaDoubanMovieService;
import com.queen.rxjava2withretrofitdemo.simpleRetrofit.SignInterceptor;

import org.reactivestreams.Subscriber;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by liukun on 2017/4/6.
 */

public class RxjavaDoubanMovieModel {
    public static final String BASE_URL = "https://api.douban.com/v2/";

    private Retrofit mRetrofit;
    private OkHttpClient client;

    private RxjavaDoubanMovieService mDoubanMovieService;

    private RxjavaDoubanMovieModel() {

        client = new OkHttpClient.Builder().addInterceptor(new SignInterceptor())
                .connectTimeout(15, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        mDoubanMovieService = mRetrofit.create(RxjavaDoubanMovieService.class);
    }

    private static RxjavaDoubanMovieModel instance;

    public static RxjavaDoubanMovieModel getInstance() {

        if (instance == null) {
            synchronized (DoubanMovieModel.class) {
                instance = new RxjavaDoubanMovieModel();
            }
        }
        return instance;

    }

    public void getMovieInTheaters(String city, Observer<DoubanMovieResult> observer){
        Observable observable = mDoubanMovieService.getMovieInTheaters(city).map(new
                HttpResultFunc<DoubanMovieResult>());

        makeSubscribe(observable, observer);

    }

    public void getMovieDetail(String movieId, Observer<DoubanMovieDetail> observer){

        Observable observable = mDoubanMovieService.getMovieDetail(movieId).map(new
                HttpResultFunc<DoubanMovieDetail>());

        makeSubscribe(observable, observer);
    }


    private <T> void makeSubscribe(Observable<T> observable, Observer<T> observer){
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }



    private class HttpResultFunc<T> implements Function<T, T>{

        @Override
        public T apply(T t) throws Exception {
            return t;
        }
    }
}
