package com.queen.rxjava2withretrofitdemo.realmEntity;

import io.realm.RealmObject;

/**
 * Created by liukun on 2017/4/6.
 */
public class RealmDoubanRating extends RealmObject {

    private int max;
    private float average;
    private String stars;
    private int min;

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

}
