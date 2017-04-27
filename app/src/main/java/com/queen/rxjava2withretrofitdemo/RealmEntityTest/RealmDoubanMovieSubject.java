package com.queen.rxjava2withretrofitdemo.RealmEntityTest;

import android.os.Parcelable;

import java.util.Iterator;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by liukun on 2017/4/6.
 */

@RealmClass
public class RealmDoubanMovieSubject implements RealmModel, Parcelable{

    @PrimaryKey
    private String id;
    private String alt;
    private String year;
    private String title;
    private String original_title;

    private int collect_count;
    private String subtype;

//    @Ignore
//    private ArrayList<String> genres;
//    private RealmList<RealmDoubanGenre> genresList;
    private RealmList<RealmDoubanGenre> genres;

    private RealmDoubanRating rating;
    private RealmDoubanAvatar images;

    private RealmList<RealmDoubanCast> casts;
    private RealmList<RealmDoubanCast> directors;

    public RealmDoubanMovieSubject() {
    }

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

    /**
     * ———————————————— ↓↓↓↓ getter and setter ↓↓↓↓ ————————————————
     */

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

    public void setCasts(RealmList<RealmDoubanCast> casts) {
        this.casts = casts;
    }

    public RealmList<RealmDoubanCast> getDirectors() {
        return directors;
    }

    public void setDirectors(RealmList<RealmDoubanCast> directors) {
        this.directors = directors;
    }

    public RealmList<RealmDoubanGenre> getGenres() {
        return genres;
    }

    public void setGenres(RealmList<RealmDoubanGenre> genres) {
        this.genres = genres;
    }

    //    public ArrayList<String> getGenres() {
//        return genres;
//    }
//
//    public void setGenres(ArrayList<String> genres) {
//        this.genres = genres;
//    }


//    public void initGenresList(){
//        if (genresList == null) {
//            genresList = new RealmList<>();
//        }
//
//        RealmDoubanGenre genre;
//        if (genres != null && genres.size() > 0) {
//            for (int i = 0, size = genres.size(); i < size; i++) {
//                genre = new RealmDoubanGenre();
//                genre.setValue(genres.get(i));
//                genresList.add(genre);
//            }
//        }
//    }

    /**
     * ———————————————— ↓↓↓↓ Parcelable code ↓↓↓↓ ————————————————
     */

    protected RealmDoubanMovieSubject(android.os.Parcel in) {
        id = in.readString();
        alt = in.readString();
        year = in.readString();
        title = in.readString();
        original_title = in.readString();
        collect_count = in.readInt();
        subtype = in.readString();
//        genres = in.createStringArrayList();
        in.readTypedList(genres, RealmDoubanGenre.CREATOR);
        rating = in.readParcelable(RealmDoubanRating.class.getClassLoader());
        images = in.readParcelable(RealmDoubanAvatar.class.getClassLoader());
        in.readTypedList(casts, RealmDoubanCast.CREATOR);
        in.readTypedList(directors, RealmDoubanCast.CREATOR);
    }

    public static final Creator<RealmDoubanMovieSubject> CREATOR = new Creator<RealmDoubanMovieSubject>() {
        @Override
        public RealmDoubanMovieSubject createFromParcel(android.os.Parcel in) {
            return new RealmDoubanMovieSubject(in);
        }

        @Override
        public RealmDoubanMovieSubject[] newArray(int size) {
            return new RealmDoubanMovieSubject[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(alt);
        dest.writeString(year);
        dest.writeString(title);
        dest.writeString(original_title);
        dest.writeInt(collect_count);
        dest.writeString(subtype);
//        dest.writeStringList(genres);
        dest.writeTypedList(genres);
        dest.writeParcelable(rating, flags);
        dest.writeParcelable(images, flags);
        dest.writeTypedList(casts);
        dest.writeTypedList(directors);
    }
}
