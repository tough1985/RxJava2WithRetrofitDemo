package com.queen.rxjava2withretrofitdemo.realmDemo.presenter;

import android.util.Log;

import com.queen.rxjava2withretrofitdemo.greenDaoDemo.model.GreenDoubanMovieModel;
import com.queen.rxjava2withretrofitdemo.greenDaoEntity.GreenDoubanMovieResult;
import com.queen.rxjava2withretrofitdemo.greenDaoEntity.GreenDoubanMovieSubject;
import com.queen.rxjava2withretrofitdemo.mvpWithRxjavaDemo.observer.LoadingObserver;
import com.queen.rxjava2withretrofitdemo.realmDemo.contract.RealmDoubanMovieContract;
import com.queen.rxjava2withretrofitdemo.realmDemo.model.RealmDoubanMovieModel;
import com.queen.rxjava2withretrofitdemo.realmEntity.RealmDoubanMovieResult;
import com.queen.rxjava2withretrofitdemo.realmEntity.RealmDoubanMovieSubject;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.realm.Realm;

/**
 * Created by liukun on 2017/4/10.
 */

public class RealmDoubanMoviePresenter implements RealmDoubanMovieContract.Presenter{

    public static final String TAG = RealmDoubanMoviePresenter.class.getSimpleName();

    private RealmDoubanMovieContract.View mView;

    public RealmDoubanMoviePresenter(RealmDoubanMovieContract.View mView) {
        this.mView = mView;
        mView.setPresenter(this);
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

                    mView.setMovies(value.getSubjects());

                    final ArrayList<RealmDoubanMovieSubject> subjects = value.getSubjects();
//                    GreenDoubanMovieModel.getInstance().saveSubjects(subjects);

                    Realm realm = Realm.getDefaultInstance();
                    Log.e(TAG, "realm.getPath()=" + realm.getPath());

                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            realm.copyToRealm(subjects);
                        }
                    });

                }
            }
        });


        RealmDoubanMovieModel.getInstance().getMovieInTheaters("上海", observer);
    }
}
