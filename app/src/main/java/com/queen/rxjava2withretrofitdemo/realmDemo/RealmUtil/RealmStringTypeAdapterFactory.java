package com.queen.rxjava2withretrofitdemo.realmDemo.RealmUtil;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.queen.rxjava2withretrofitdemo.RealmEntityTest.RealmDoubanGenre;

import io.realm.RealmList;

/**
 * Created by liukun on 2017/4/12.
 */

public class RealmStringTypeAdapterFactory implements TypeAdapterFactory{

    public final static String TAG = RealmStringTypeAdapterFactory.class.getSimpleName();

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {


        TypeToken<RealmList<RealmDoubanGenre>> realmListGenreTypeToken =  new TypeToken<RealmList<RealmDoubanGenre>>(){};
//        String realmListGenreTypeTokenName = realmListGenreTypeToken.getRawType().getSimpleName();

        if (realmListGenreTypeToken.getType().equals(type.getType())) {
            Log.e(TAG, "equals");
            return (TypeAdapter<T>) RealmDoubanGenreListTypeAdapter.INSTANCE;
        }

//        Log.e(TAG, "type.getRawType().getSimpleName()=" + type.getRawType().getSimpleName());
//        Log.e(TAG, "realmListGenreTypeTokenName=" + realmListGenreTypeTokenName);

        return null;
    }
}
