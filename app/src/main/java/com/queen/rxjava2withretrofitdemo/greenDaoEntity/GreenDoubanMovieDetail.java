package com.queen.rxjava2withretrofitdemo.greenDaoEntity;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by liukun on 2017/4/6.
 */

public class GreenDoubanMovieDetail {

    private GreenDoubanRating rating;
    private String id;
    private String title;
    private ArrayList<String> countries;
    private ArrayList<String> genres;
    private String year;
    private String summary;
    private GreenDoubanAvatar images;
    private ArrayList<GreenDoubanCast> casts;
    private ArrayList<GreenDoubanCast> directors;

    public GreenDoubanRating getRating() {
        return rating;
    }

    public void setRating(GreenDoubanRating rating) {
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getCountries() {
        return countries;
    }

    public void setCountries(ArrayList<String> countries) {
        this.countries = countries;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public GreenDoubanAvatar getImages() {
        return images;
    }

    public void setImages(GreenDoubanAvatar images) {
        this.images = images;
    }

    public ArrayList<GreenDoubanCast> getCasts() {
        return casts;
    }

    public void setCasts(ArrayList<GreenDoubanCast> casts) {
        this.casts = casts;
    }

    public ArrayList<GreenDoubanCast> getDirectors() {
        return directors;
    }

    public void setDirectors(ArrayList<GreenDoubanCast> directors) {
        this.directors = directors;
    }

    public String getDirectorNames(){
        return getNames(directors);
    }

    public String getCastsNames(){
        return getNames(casts);
    }

    public String getContriesString(){
        return buildStr(countries);
    }

    public String getGenresStr(){
        return buildStr(genres);
    }

    private String buildStr(ArrayList<String> list){
        StringBuilder sb = new StringBuilder();
        if (list != null) {
            Iterator<String> i = list.iterator();
            String item;
            while (i.hasNext()){
                item = i.next();
                sb.append(item);
                if (i.hasNext()) {
                    sb.append(" / ");
                }
            }
        }
        return sb.toString();
    }

    private String getNames(ArrayList<GreenDoubanCast> doubanCastList){
        StringBuilder sb = new StringBuilder();
        if (doubanCastList != null) {
            Iterator<GreenDoubanCast> i = doubanCastList.iterator();
            GreenDoubanCast cast;
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
}
