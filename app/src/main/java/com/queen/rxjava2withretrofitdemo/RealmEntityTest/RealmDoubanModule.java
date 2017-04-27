package com.queen.rxjava2withretrofitdemo.RealmEntityTest;

import io.realm.annotations.RealmModule;

/**
 * Created by liukun on 2017/4/12.
 */

@RealmModule(classes = {RealmDoubanAvatar.class, RealmDoubanGenre.class, RealmDoubanRating.class, RealmDoubanCast
        .class, RealmDoubanMovieSubject.class})
public class RealmDoubanModule {
}
