package com.queen.rxjava2withretrofitdemo.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Iterator;
import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

/**
 * Created by liukun on 2017/4/6.
 */
public class DoubanMovieSubject {

    private String id;
    private String alt;
    private String year;

    private Long doubanRatingId;
    private DoubanRating rating;

    private String title;
    private String original_title;
    private int collect_count;
    private String subtype;

    private Long doubanAvatarId;
    private DoubanAvatar images;


    private List<DoubanCast> casts;


    private List<DoubanCast> directors;


    public String getDirectorNames(){
        return getNames(directors);
    }

    public String getCastsNames(){
        return getNames(casts);
    }

    private String getNames(List<DoubanCast> doubanCastList){
        StringBuilder sb = new StringBuilder();
        if (doubanCastList != null) {
            Iterator<DoubanCast> i = doubanCastList.iterator();
            DoubanCast cast;
            while (i.hasNext()){
                cast = i.next();
                sb.append(cast.getName());
                if (i.hasNext()) {
                    sb.append(" / ");
                }
            }
        }
        return sb.toString();
    }




    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlt() {
        return this.alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getYear() {
        return this.year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginal_title() {
        return this.original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public int getCollect_count() {
        return this.collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public String getSubtype() {
        return this.subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public DoubanRating getRating() {

        return rating;
    }

    public void setRating(DoubanRating rating) {
            this.rating = rating;
    }
    public DoubanAvatar getImages() {

        return images;
    }
    public void setImages(DoubanAvatar images) {
            this.images = images;
    }

    public List<DoubanCast> getCasts() {

        return casts;
    }

    public List<DoubanCast> getDirectors() {

        return directors;
    }


}
