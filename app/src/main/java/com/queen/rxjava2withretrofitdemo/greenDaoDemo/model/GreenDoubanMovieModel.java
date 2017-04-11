package com.queen.rxjava2withretrofitdemo.greenDaoDemo.model;

import com.queen.rxjava2withretrofitdemo.App;
import com.queen.rxjava2withretrofitdemo.dao.DaoSession;
import com.queen.rxjava2withretrofitdemo.dao.GreenDoubanAvatarDao;
import com.queen.rxjava2withretrofitdemo.dao.GreenDoubanCastDao;
import com.queen.rxjava2withretrofitdemo.dao.GreenDoubanRatingDao;
import com.queen.rxjava2withretrofitdemo.dao.JoinCastDao;
import com.queen.rxjava2withretrofitdemo.dao.JoinDirectorDao;
import com.queen.rxjava2withretrofitdemo.greenDaoDemo.service.GreenDoubanMovieService;
import com.queen.rxjava2withretrofitdemo.greenDaoEntity.GreenDoubanAvatar;
import com.queen.rxjava2withretrofitdemo.greenDaoEntity.GreenDoubanCast;
import com.queen.rxjava2withretrofitdemo.greenDaoEntity.GreenDoubanMovieDetail;
import com.queen.rxjava2withretrofitdemo.greenDaoEntity.GreenDoubanMovieResult;
import com.queen.rxjava2withretrofitdemo.greenDaoEntity.GreenDoubanMovieSubject;
import com.queen.rxjava2withretrofitdemo.greenDaoEntity.GreenDoubanRating;
import com.queen.rxjava2withretrofitdemo.greenDaoEntity.JoinCast;
import com.queen.rxjava2withretrofitdemo.greenDaoEntity.JoinDirector;
import com.queen.rxjava2withretrofitdemo.mvpDemo.model.DoubanMovieModel;
import com.queen.rxjava2withretrofitdemo.simpleRetrofit.SignInterceptor;

import java.util.List;
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
 * Created by liukun on 2017/4/6.
 */

public class GreenDoubanMovieModel {
    public static final String BASE_URL = "https://api.douban.com/v2/";

    private Retrofit mRetrofit;
    private OkHttpClient client;

    private GreenDoubanMovieService mDoubanMovieService;

    private GreenDoubanMovieModel() {

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

        mDoubanMovieService = mRetrofit.create(GreenDoubanMovieService.class);
    }

    private static GreenDoubanMovieModel instance;

    public static GreenDoubanMovieModel getInstance() {

        if (instance == null) {
            synchronized (DoubanMovieModel.class) {
                instance = new GreenDoubanMovieModel();
            }
        }
        return instance;

    }

    public void getMovieInTheaters(String city, Observer<GreenDoubanMovieResult> observer){
        Observable observable = mDoubanMovieService.getMovieInTheaters(city).map(new HttpResultFunc<GreenDoubanMovieResult>());

        makeSubscribe(observable, observer);
    }

    public void getMovieDetail(String movieId, Observer<GreenDoubanMovieResult> observer){

        Observable observable = mDoubanMovieService.getMovieDetail(movieId).map(new HttpResultFunc<GreenDoubanMovieDetail>());

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

    public void saveSubjects(List<GreenDoubanMovieSubject> subjects){
        DaoSession mSessioin = App.getInstance().getDaoSession();

        GreenDoubanRatingDao ratingDao = mSessioin.getGreenDoubanRatingDao();
        GreenDoubanRating rating;

        GreenDoubanAvatarDao avatarDao = mSessioin.getGreenDoubanAvatarDao();
        GreenDoubanAvatar image;

        GreenDoubanCastDao castDao = mSessioin.getGreenDoubanCastDao();
        JoinCastDao joinCastDao = mSessioin.getJoinCastDao();
        JoinCast joinCast;

        JoinDirectorDao joinDirectorDao = mSessioin.getJoinDirectorDao();
        JoinDirector joinDirector;

        GreenDoubanAvatar castAvatar;

        for (GreenDoubanMovieSubject subject : subjects) {
            //保存评分
            rating = subject.getOriginRating();
            ratingDao.insertOrReplace(rating);
            subject.setRating(rating);

            //保存image
            image = subject.getOriginImages();
            avatarDao.insertOrReplace(image);
            subject.setImages(image);

            List<GreenDoubanCast> castList = subject.getCasts();
            List<GreenDoubanCast> directorList = subject.getDirectors();

            //保存电影subject
            Long subjectGid = mSessioin.getGreenDoubanMovieSubjectDao().insertOrReplace(subject);

            //保存演员
            for (GreenDoubanCast cast: castList) {

                castAvatar = cast.getOriginAvatars();
                if (castAvatar != null) {
                    avatarDao.insertOrReplace(castAvatar);
                    cast.setAvatars(castAvatar);

                    Long castGid = castDao.insert(cast);
                    joinCast = new JoinCast(subjectGid, castGid);
                    joinCastDao.insert(joinCast);
                }

            }

            //保存导演
            for (GreenDoubanCast cast: directorList) {

                castAvatar = cast.getOriginAvatars();
                if (castAvatar != null) {
                    avatarDao.insertOrReplace(castAvatar);
                    cast.setAvatars(castAvatar);

                    Long castGid = castDao.insert(cast);
                    joinDirector = new JoinDirector(subjectGid, castGid);
                    joinDirectorDao.insert(joinDirector);
                }
            }

        }


    }
}
