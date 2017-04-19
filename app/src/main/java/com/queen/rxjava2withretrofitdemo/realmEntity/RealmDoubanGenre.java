package com.queen.rxjava2withretrofitdemo.realmEntity;

import android.os.Parcelable;

import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by liukun on 2017/4/11.
 */
@RealmClass
public class RealmDoubanGenre implements RealmModel, Parcelable{

    @PrimaryKey
//    private String name;
    private String value;

    public RealmDoubanGenre() {
    }

    /**
     * ———————————————— ↓↓↓↓ getter and setter ↓↓↓↓ ————————————————
     */

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /**
     * ———————————————— ↓↓↓↓ Parcelable code ↓↓↓↓ ————————————————
     */
    protected RealmDoubanGenre(android.os.Parcel in) {
        value = in.readString();
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
        dest.writeString(value);
    }

}
