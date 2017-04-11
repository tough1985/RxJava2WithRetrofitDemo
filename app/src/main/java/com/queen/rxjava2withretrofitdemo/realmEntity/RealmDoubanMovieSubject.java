package com.queen.rxjava2withretrofitdemo.realmEntity;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.RealmModule;

/**
 * Created by liukun on 2017/4/6.
 */
@RealmClass
public class RealmDoubanMovieSubject implements RealmModel{
//public class RealmDoubanMovieSubject extends RealmObject{

    @PrimaryKey
    private String id;
    private String alt;
    private String year;
    private String title;
    private String original_title;

    private int collect_count;
    private String subtype;

    @Ignore
    private ArrayList<String> genres;
    private RealmList<RealmDoubanGenre> genresList;


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

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public RealmList<RealmDoubanGenre> getGenresList() {

        return genresList;
    }

    public void setGenresList(RealmList<RealmDoubanGenre> genresList) {
        this.genresList = genresList;
    }

    public void initGenresList(){
        if (genresList == null) {
            genresList = new RealmList<>();
        }

        RealmDoubanGenre genre;
        if (genres != null && genres.size() > 0) {
            for (int i = 0, size = genres.size(); i < size; i++) {
                genre = new RealmDoubanGenre();
                genre.setName(genres.get(i));
                genresList.add(genre);
            }
        }
    }
}
