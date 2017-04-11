package com.queen.rxjava2withretrofitdemo.realmDemo.model;

import com.queen.rxjava2withretrofitdemo.greenDaoDemo.model.GreenDoubanMovieModel;
import com.queen.rxjava2withretrofitdemo.greenDaoDemo.service.GreenDoubanMovieService;
import com.queen.rxjava2withretrofitdemo.greenDaoEntity.GreenDoubanMovieDetail;
import com.queen.rxjava2withretrofitdemo.greenDaoEntity.GreenDoubanMovieResult;
import com.queen.rxjava2withretrofitdemo.mvpDemo.model.DoubanMovieModel;
import com.queen.rxjava2withretrofitdemo.realmDemo.adapter.RealmDoubanMovieAdapter;
import com.queen.rxjava2withretrofitdemo.realmDemo.service.RealmDoubanMovieService;
import com.queen.rxjava2withretrofitdemo.realmEntity.RealmDoubanMovieDetail;
import com.queen.rxjava2withretrofitdemo.realmEntity.RealmDoubanMovieResult;
import com.queen.rxjava2withretrofitdemo.simpleRetrofit.SignInterceptor;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by liukun on 2017/4/10.
 */

public class RealmDoubanMovieModel {
    public static final String BASE_URL = "https://api.douban.com/v2/";

    private Retrofit mRetrofit;
    private OkHttpClient client;

    private RealmDoubanMovieService mDoubanMovieService;

    private RealmDoubanMovieModel() {

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

        mDoubanMovieService = mRetrofit.create(RealmDoubanMovieService.class);
    }

    private static RealmDoubanMovieModel instance;

    public static RealmDoubanMovieModel getInstance() {

        if (instance == null) {
            synchronized (DoubanMovieModel.class) {
                instance = new RealmDoubanMovieModel();
            }
        }
        return instance;

    }

    public void getMovieInTheaters(String city, Observer<RealmDoubanMovieResult> observer){
        Observable observable = mDoubanMovieService.getMovieInTheaters(city).map(new
                HttpResultFunc<RealmDoubanMovieResult>());

        makeSubscribe(observable, observer);
    }

    public void getMovieDetail(String movieId, Observer<RealmDoubanMovieResult> observer){

        Observable observable = mDoubanMovieService.getMovieDetail(movieId).map(new HttpResultFunc<RealmDoubanMovieDetail>());

        makeSubscribe(observable, observer);
    }


    private <T> void makeSubscribe(Observable<T> observable, Observer<T> observer){
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }



    private class HttpResultFunc<T> implements Function<T, T> {

        @Override
        public T apply(T t) throws Exception {
            return t;
        }
    }
}
