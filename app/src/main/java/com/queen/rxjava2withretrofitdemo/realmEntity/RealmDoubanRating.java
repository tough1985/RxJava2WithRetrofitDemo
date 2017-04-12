package com.queen.rxjava2withretrofitdemo.realmEntity;

import android.os.Parcelable;

import io.realm.RealmObject;

/**
 * Created by liukun on 2017/4/6.
 */
public class RealmDoubanRating extends RealmObject implements Parcelable{

    private int max;
    private float average;
    private String stars;
    private int min;

    public RealmDoubanRating() {
    }

    /**
     * ———————————————— ↓↓↓↓ getter and setter ↓↓↓↓ ————————————————
     */

    public int getMax() {
        return this.max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public float getAverage() {
        return this.average;
    }

    public void setAverage(float average) {
        this.average = average;
    }

    public String getStars() {
        return this.stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public int getMin() {
        return this.min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    /**
     * ———————————————— ↓↓↓↓ Parcelable code ↓↓↓↓ ————————————————
     */

    protected RealmDoubanRating(android.os.Parcel in) {
        max = in.readInt();
        average = in.readFloat();
        stars = in.readString();
        min = in.readInt();
    }

    public static final Creator<RealmDoubanRating> CREATOR = new Creator<RealmDoubanRating>() {
        @Override
        public RealmDoubanRating createFromParcel(android.os.Parcel in) {
            return new RealmDoubanRating(in);
        }

        @Override
        public RealmDoubanRating[] newArray(int size) {
            return new RealmDoubanRating[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeInt(max);
        dest.writeFloat(average);
        dest.writeString(stars);
        dest.writeInt(min);
    }
}
