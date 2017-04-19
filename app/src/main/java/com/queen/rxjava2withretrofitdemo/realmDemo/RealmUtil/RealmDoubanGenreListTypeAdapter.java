package com.queen.rxjava2withretrofitdemo.realmDemo.RealmUtil;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.queen.rxjava2withretrofitdemo.realmEntity.RealmDoubanGenre;

import java.io.IOException;

import io.realm.RealmList;

/**
 * Created by liukun on 2017/4/12.
 */

public class RealmDoubanGenreListTypeAdapter extends TypeAdapter<RealmList<RealmDoubanGenre>> {

    public static final TypeAdapter<RealmList<RealmDoubanGenre>> INSTANCE =
            new RealmDoubanGenreListTypeAdapter().nullSafe();

    private RealmDoubanGenreListTypeAdapter() { }

    @Override
    public void write(JsonWriter out, RealmList<RealmDoubanGenre> value) throws IOException {
        out.beginArray();
        for(RealmDoubanGenre realmDoubanGenre : value) { out.value(realmDoubanGenre.getValue()); }
        out.endArray();
    }

    @Override
    public RealmList<RealmDoubanGenre> read(JsonReader in) throws IOException {

        RealmList<RealmDoubanGenre> realmStrings = new RealmList<>();
        in.beginArray();
        while (in.hasNext()) {
            if(in.peek() == JsonToken.NULL) {
                in.nextNull();
            } else {
                RealmDoubanGenre realmString = new RealmDoubanGenre();
                realmString.setValue(in.nextString());
                realmStrings.add(realmString);
            }
        }
        in.endArray();
        return realmStrings;
    }
}
