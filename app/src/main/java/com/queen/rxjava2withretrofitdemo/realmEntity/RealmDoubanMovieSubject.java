package com.queen.rxjava2withretrofitdemo.realmEntity;

import java.util.Iterator;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by liukun on 2017/4/6.
 */
public class RealmDoubanMovieSubject extends RealmObject{

    private String id;
    private String alt;
    private String year;
    private String title;
    private String original_title;

    private int collect_count;
    private String subtype;

    private RealmDoubanRating rating;
    private RealmDoubanAvatar images;

    private RealmList<RealmDoubanCast> casts;
    private RealmList<RealmDoubanCast> directors;


    public String getDirectorNames(){
        return getNames(directors);
    }

    public String getCastsNames(){
        return getNames(casts);
    }

    private String getNames(List<RealmDoubanCast> doubanCastList){
        StringBuilder sb = new StringBuilder();
        if (doubanCastList != null) {
            Iterator<RealmDoubanCast> i = doubanCastList.iterator();
            RealmDoubanCast cast;
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

    public RealmDoubanRating getRating() {

        return rating;
    }

    public void setRating(RealmDoubanRating rating) {
            this.rating = rating;
    }
    public RealmDoubanAvatar getImages() {

        return images;
    }
    public void setImages(RealmDoubanAvatar images) {
            this.images = images;
    }

    public RealmList<RealmDoubanCast> getCasts() {

        return casts;
    }

    public RealmList<RealmDoubanCast> getDirectors() {

        return directors;
    }


}
