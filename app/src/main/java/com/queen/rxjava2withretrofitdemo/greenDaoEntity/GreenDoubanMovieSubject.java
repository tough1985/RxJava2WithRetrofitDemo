package com.queen.rxjava2withretrofitdemo.greenDaoEntity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Iterator;
import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.queen.rxjava2withretrofitdemo.dao.DaoSession;
import com.queen.rxjava2withretrofitdemo.dao.GreenDoubanCastDao;
import com.queen.rxjava2withretrofitdemo.dao.GreenDoubanAvatarDao;
import com.queen.rxjava2withretrofitdemo.dao.GreenDoubanRatingDao;
import com.queen.rxjava2withretrofitdemo.dao.GreenDoubanMovieSubjectDao;

/**
 * Created by liukun on 2017/4/6.
 */
@Entity
public class GreenDoubanMovieSubject {

    @Id
    private Long gId;

    private String id;
    private String alt;
    private String year;

    private Long doubanRatingId;
    @ToOne(joinProperty = "doubanRatingId")
    private GreenDoubanRating rating;

    private String title;
    private String original_title;
    private int collect_count;
    private String subtype;

    private Long doubanAvatarId;

    @ToOne(joinProperty = "doubanAvatarId")
    private GreenDoubanAvatar images;


    @ToMany
    @JoinEntity(
            entity = JoinCast.class,
            sourceProperty = "subjectId",
            targetProperty = "castId"
    )
    private List<GreenDoubanCast> casts;

    @ToMany
    @JoinEntity(
            entity = JoinDirector.class,
            sourceProperty = "subjectId",
            targetProperty = "directorId"
    )
    private List<GreenDoubanCast> directors;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1971669303)
    private transient GreenDoubanMovieSubjectDao myDao;


    @Generated(hash = 1638798059)
    public GreenDoubanMovieSubject(Long gId, String id, String alt, String year,
            Long doubanRatingId, String title, String original_title,
            int collect_count, String subtype, Long doubanAvatarId) {
        this.gId = gId;
        this.id = id;
        this.alt = alt;
        this.year = year;
        this.doubanRatingId = doubanRatingId;
        this.title = title;
        this.original_title = original_title;
        this.collect_count = collect_count;
        this.subtype = subtype;
        this.doubanAvatarId = doubanAvatarId;
    }

    @Generated(hash = 973725492)
    public GreenDoubanMovieSubject() {
    }

    @Generated(hash = 1876602107)
    private transient Long rating__resolvedKey;

    @Generated(hash = 1730634245)
    private transient Long images__resolvedKey;


    public String getDirectorNames(){
        return getNames(directors);
    }

    public String getCastsNames(){
        return getNames(casts);
    }

    private String getNames(List<GreenDoubanCast> doubanCastList){
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

    public Long getGId() {
        return this.gId;
    }

    public void setGId(Long gId) {
        this.gId = gId;
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

    public Long getDoubanRatingId() {
        return this.doubanRatingId;
    }

    public void setDoubanRatingId(Long doubanRatingId) {
        this.doubanRatingId = doubanRatingId;
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

    public Long getDoubanAvatarId() {
        return this.doubanAvatarId;
    }

    public void setDoubanAvatarId(Long doubanAvatarId) {
        this.doubanAvatarId = doubanAvatarId;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 2105051048)
    public GreenDoubanRating getRating() {
        Long __key = this.doubanRatingId;
        if (rating__resolvedKey == null || !rating__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            GreenDoubanRatingDao targetDao = daoSession.getGreenDoubanRatingDao();
            GreenDoubanRating ratingNew = targetDao.load(__key);
            synchronized (this) {
                rating = ratingNew;
                rating__resolvedKey = __key;
            }
        }
        return rating;
    }

    public GreenDoubanRating getOriginRating(){
        return rating;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2074912051)
    public void setRating(GreenDoubanRating rating) {
        synchronized (this) {
            this.rating = rating;
            doubanRatingId = rating == null ? null : rating.getGId();
            rating__resolvedKey = doubanRatingId;
        }
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 2146960013)
    public GreenDoubanAvatar getImages() {
        Long __key = this.doubanAvatarId;
        if (images__resolvedKey == null || !images__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            GreenDoubanAvatarDao targetDao = daoSession.getGreenDoubanAvatarDao();
            GreenDoubanAvatar imagesNew = targetDao.load(__key);
            synchronized (this) {
                images = imagesNew;
                images__resolvedKey = __key;
            }
        }
        return images;
    }

    public GreenDoubanAvatar getOriginImages() {
        return images;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 953770797)
    public void setImages(GreenDoubanAvatar images) {
        synchronized (this) {
            this.images = images;
            doubanAvatarId = images == null ? null : images.getGId();
            images__resolvedKey = doubanAvatarId;
        }
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 2041233192)
    public List<GreenDoubanCast> getCasts() {
        if (casts == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            GreenDoubanCastDao targetDao = daoSession.getGreenDoubanCastDao();
            List<GreenDoubanCast> castsNew = targetDao
                    ._queryGreenDoubanMovieSubject_Casts(gId);
            synchronized (this) {
                if (casts == null) {
                    casts = castsNew;
                }
            }
        }
        return casts;
    }

    public List<GreenDoubanCast> getOriginCasts() {
        return casts;
    }

    public void setCasts(List<GreenDoubanCast> casts) {
        this.casts = casts;
    }

    public void setDirectors(List<GreenDoubanCast> directors) {
        this.directors = directors;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1745563101)
    public synchronized void resetCasts() {
        casts = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 667184497)
    public List<GreenDoubanCast> getDirectors() {
        if (directors == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            GreenDoubanCastDao targetDao = daoSession.getGreenDoubanCastDao();
            List<GreenDoubanCast> directorsNew = targetDao
                    ._queryGreenDoubanMovieSubject_Directors(gId);
            synchronized (this) {
                if (directors == null) {
                    directors = directorsNew;
                }
            }
        }
        return directors;
    }

    public List<GreenDoubanCast> getOriginDirectors() {
        return directors;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1604249528)
    public synchronized void resetDirectors() {
        directors = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1221631515)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getGreenDoubanMovieSubjectDao()
                : null;
    }




//    public String getId() {
//        return this.id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getAlt() {
//        return this.alt;
//    }
//
//    public void setAlt(String alt) {
//        this.alt = alt;
//    }
//
//    public String getYear() {
//        return this.year;
//    }
//
//    public void setYear(String year) {
//        this.year = year;
//    }
//
//    public String getTitle() {
//        return this.title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getOriginal_title() {
//        return this.original_title;
//    }
//
//    public void setOriginal_title(String original_title) {
//        this.original_title = original_title;
//    }
//
//    public int getCollect_count() {
//        return this.collect_count;
//    }
//
//    public void setCollect_count(int collect_count) {
//        this.collect_count = collect_count;
//    }
//
//    public String getSubtype() {
//        return this.subtype;
//    }
//
//    public void setSubtype(String subtype) {
//        this.subtype = subtype;
//    }
//
//    public GreenDoubanRating getRating() {
//
//        return rating;
//    }
//
//    public void setRating(GreenDoubanRating rating) {
//            this.rating = rating;
//    }
//    public GreenDoubanAvatar getImages() {
//
//        return images;
//    }
//    public void setImages(GreenDoubanAvatar images) {
//            this.images = images;
//    }
//
//    public List<GreenDoubanCast> getCasts() {
//
//        return casts;
//    }
//
//    public List<GreenDoubanCast> getDirectors() {
//
//        return directors;
//    }


}
