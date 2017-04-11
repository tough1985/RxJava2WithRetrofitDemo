package com.queen.rxjava2withretrofitdemo.realmEntity;

import java.util.ArrayList;

/**
 * Created by liukun on 2017/4/6.
 */

public class RealmDoubanMovieResult {

    private int count;
    private int start;
    private int total;
    private String title;
    private ArrayList<RealmDoubanMovieSubject> subjects;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<RealmDoubanMovieSubject> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<RealmDoubanMovieSubject> subjects) {
        this.subjects = subjects;
    }
}
