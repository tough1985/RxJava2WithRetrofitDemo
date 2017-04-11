package com.queen.rxjava2withretrofitdemo.realmDemo.presenter;

import android.util.Log;

import com.queen.rxjava2withretrofitdemo.mvpWithRxjavaDemo.observer.LoadingObserver;
import com.queen.rxjava2withretrofitdemo.realmDemo.contract.RealmDoubanMovieContract;
import com.queen.rxjava2withretrofitdemo.realmDemo.model.RealmDoubanMovieModel;
import com.queen.rxjava2withretrofitdemo.realmEntity.RealmDoubanMovieResult;
import com.queen.rxjava2withretrofitdemo.realmEntity.RealmDoubanMovieSubject;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by liukun on 2017/4/10.
 */

public class RealmDoubanMoviePresenter implements RealmDoubanMovieContract.Presenter{

    public static final String TAG = RealmDoubanMoviePresenter.class.getSimpleName();

    private RealmDoubanMovieContract.View mView;

    private Realm realm;

    public RealmDoubanMoviePresenter(RealmDoubanMovieContract.View mView) {
        this.mView = mView;
        mView.setPresenter(this);
        realm = Realm.getDefaultInstance();
    }

    @Override
    public void start() {
        Observer observer = new LoadingObserver<RealmDoubanMovieResult>(mView, new LoadingObserver
                .ObserverOnNextListener<RealmDoubanMovieResult>() {
            @Override
            public void observerOnNext(RealmDoubanMovieResult value) {
                //如果返回数据为空，显示空态
                if (value == null || value.getSubjects() == null || value.getSubjects().size() == 0) {
                    mView.showNull();
                } else {



                    final ArrayList<RealmDoubanMovieSubject> subjects = value.getSubjects();

                    Log.e(TAG, "realm.getPath()=" + realm.getPath());

                    for (int i = 0; i < subjects.size(); i++) {
                        subjects.get(i).initGenresList();
                    }

                    realm.executeTransactionAsync(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            Log.e(TAG, "execute : Thread.currentThread().getName()=" + Thread.currentThread().getName
                                    ());
//                            realm.copyToRealm(subjects);
//                            realm.copyToRealm(subjects);
                            realm.copyToRealmOrUpdate(subjects);
                        }
                    }, new Realm.Transaction.OnSuccess() {
                        @Override
                        public void onSuccess() {
                            Log.e(TAG, "onSuccess : Thread.currentThread().getName()=" + Thread.currentThread().getName
                                    ());
                            RealmResults<RealmDoubanMovieSubject> subjectRealmList = realm.where(RealmDoubanMovieSubject.class)
                                    .findAllAsync();
                            ArrayList<RealmDoubanMovieSubject> list = new ArrayList<>(subjectRealmList);

                            Log.e(TAG, "list.size()=" + list.size());

                            mView.setMovies(list);

                        }
                    });

                }
            }
        });


        RealmDoubanMovieModel.getInstance().getMovieInTheaters("上海", observer);
    }
}
