package com.queen.rxjava2withretrofitdemo.realmEntity;

import android.os.Parcelable;

import org.parceler.Parcel;

import io.realm.RealmDoubanGenreRealmProxy;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by liukun on 2017/4/11.
 */
public class RealmDoubanGenre extends RealmObject implements Parcelable{

    @PrimaryKey
    private String name;

    public RealmDoubanGenre() {
    }

    /**
     * ———————————————— ↓↓↓↓ getter and setter ↓↓↓↓ ————————————————
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    /**
     * ———————————————— ↓↓↓↓ Parcelable code ↓↓↓↓ ————————————————
     */
    protected RealmDoubanGenre(android.os.Parcel in) {
        name = in.readString();
    }

    public static final Creator<RealmDoubanGenre> CREATOR = new Creator<RealmDoubanGenre>() {
        @Override
        public RealmDoubanGenre createFromParcel(android.os.Parcel in) {
            return new RealmDoubanGenre(in);
        }

        @Override
        public RealmDoubanGenre[] newArray(int size) {
            return new RealmDoubanGenre[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeString(name);
    }
}
