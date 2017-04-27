package com.queen.rxjava2withretrofitdemo.realmDemo.presenter;

import android.util.Log;

import com.orhanobut.logger.Logger;
import com.queen.mvplib.MvpBasePresenter;
import com.queen.rxjava2withretrofitdemo.BuildConfig;
import com.queen.rxjava2withretrofitdemo.mvpWithRxjavaDemo.observer.LoadingObserver;
import com.queen.rxjava2withretrofitdemo.realmDemo.contract.RealmDoubanMovieContract;
import com.queen.rxjava2withretrofitdemo.realmDemo.model.RealmDoubanMovieModel;
import com.queen.rxjava2withretrofitdemo.RealmEntityTest.RealmDoubanMovieResult;
import com.queen.rxjava2withretrofitdemo.RealmEntityTest.RealmDoubanMovieSubject;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by liukun on 2017/4/10.
 */

public class RealmDoubanMoviePresenter extends MvpBasePresenter<RealmDoubanMovieContract.View> implements
        RealmDoubanMovieContract.Presenter<RealmDoubanMovieContract.View>{

    public static final String TAG = RealmDoubanMoviePresenter.class.getSimpleName();

//    private RealmDoubanMovieContract.View mView;

    private Realm realm;

    public RealmDoubanMoviePresenter() {
        realm = Realm.getDefaultInstance();
        
    }

//    public RealmDoubanMoviePresenter(RealmDoubanMovieContract.View mView) {
//        this.mView = mView;
////        mView.setPresenter(this);
//        realm = Realm.getDefaultInstance();
//    }

    @Override
    public void start() {
        Observer observer = new LoadingObserver<RealmDoubanMovieResult>(getView(), new LoadingObserver
                .ObserverOnNextListener<RealmDoubanMovieResult>() {
            @Override
            public void observerOnNext(RealmDoubanMovieResult value) {
                //如果返回数据为空，显示空态
                if (value == null || value.getSubjects() == null || value.getSubjects().size() == 0) {
                    if (isViewAttached()) {
                        getView().showNull();
                    }
                } else {

                    final ArrayList<RealmDoubanMovieSubject> subjects = value.getSubjects();

                    if (BuildConfig.DEBUG) {
                        Logger.t(TAG).e("realm.getPath()=" + realm.getPath());
                    }


//                    for (int i = 0; i < subjects.size(); i++) {
//                        subjects.get(i).initGenresList();
//                    }

                    realm.executeTransactionAsync(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            Logger.t(TAG).e("execute : Thread.currentThread().getName()=" + Thread.currentThread().getName
                                    ());
//                            realm.copyToRealm(subjects);
//                            realm.copyToRealm(subjects);
                            realm.copyToRealmOrUpdate(subjects);
                        }
                    }, new Realm.Transaction.OnSuccess() {
                        @Override
                        public void onSuccess() {
                            Logger.t(TAG).e("onSuccess : Thread.currentThread().getName()=" + Thread.currentThread().getName());

                            RealmResults<RealmDoubanMovieSubject> subjectRealmList = realm.where(RealmDoubanMovieSubject.class)
                                    .findAllAsync();
                            ArrayList<RealmDoubanMovieSubject> list = new ArrayList<>(subjectRealmList);

                            Log.e(TAG, "list.size()=" + list.size());

                            if (isViewAttached()) {

                                getView().setMovies(list);
                            }

                        }
                    });

                }
            }
        });


        RealmDoubanMovieModel.getInstance().getMovieInTheaters("上海", observer);
    }


}
