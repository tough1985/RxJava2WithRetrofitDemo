package com.queen.rxjava2withretrofitdemo.greenDaoDemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.queen.rxjava2withretrofitdemo.R;
import com.queen.rxjava2withretrofitdemo.entity.DoubanMovieSubject;
import com.queen.rxjava2withretrofitdemo.greenDaoEntity.GreenDoubanMovieSubject;
import com.queen.rxjava2withretrofitdemo.mvpDemo.adapter.DoubanMovieAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liukun on 2017/4/10.
 */

public class GreenDoubanMovieAdapter extends BaseAdapter {

    private ArrayList<GreenDoubanMovieSubject> mDoubanMovieSubject;
    private Context mContext;
    private LayoutInflater inflater;

    public GreenDoubanMovieAdapter(Context context, ArrayList<GreenDoubanMovieSubject> doubanMovieSubject) {
        mDoubanMovieSubject = doubanMovieSubject;
        mContext = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (mDoubanMovieSubject == null) {
            return 0;
        }

        return mDoubanMovieSubject.size();
    }

    @Override
    public GreenDoubanMovieSubject getItem(int position) {
        return mDoubanMovieSubject.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GreenDoubanMovieAdapter.ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_douban_movie, parent, false);
            holder = new GreenDoubanMovieAdapter.ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (GreenDoubanMovieAdapter.ViewHolder) convertView.getTag();
        }

        final GreenDoubanMovieSubject doubanMovieSubject = getItem(position);
        holder.itemDoubanMovieDirectorsTV.setText("导演：" + doubanMovieSubject.getDirectorNames());
        holder.itemDoubanMovieCastsTV.setText("主演：" + doubanMovieSubject.getCastsNames());
        holder.itemDoubanMovieRatingTV.setText(String.valueOf(doubanMovieSubject.getOriginRating().getAverage()));
        holder.itemDoubanMovieWatchedTV.setText(doubanMovieSubject.getCollect_count() + "人看过");
        holder.itemDoubanMovieTitleTV.setText(doubanMovieSubject.getTitle());

        Glide.with(mContext).load(doubanMovieSubject.getOriginImages().getLarge()).into(holder.itemDoubanMovieAvatarIV);


        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.item_douban_movie_content)
        LinearLayout itemDoubanMovieContent;
        @BindView(R.id.item_douban_movie_avatar_IV)
        ImageView itemDoubanMovieAvatarIV;
        @BindView(R.id.item_douban_movie_title_TV)
        TextView itemDoubanMovieTitleTV;
        @BindView(R.id.item_douban_movie_rating_TV)
        TextView itemDoubanMovieRatingTV;
        @BindView(R.id.item_douban_movie_directors_TV)
        TextView itemDoubanMovieDirectorsTV;
        @BindView(R.id.item_douban_movie_casts_TV)
        TextView itemDoubanMovieCastsTV;
        @BindView(R.id.item_douban_movie_watched_TV)
        TextView itemDoubanMovieWatchedTV;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
