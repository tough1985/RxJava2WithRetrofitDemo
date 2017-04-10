package com.queen.rxjava2withretrofitdemo.greenDaoEntity;

import java.util.ArrayList;

/**
 * Created by liukun on 2017/4/6.
 */

public class GreenDoubanMovieResult {

    private int count;
    private int start;
    private int total;
    private String title;
    private ArrayList<GreenDoubanMovieSubject> subjects;

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

    public ArrayList<GreenDoubanMovieSubject> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<GreenDoubanMovieSubject> subjects) {
        this.subjects = subjects;
    }
}
