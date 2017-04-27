package com.queen.rxjava2withretrofitdemo.realmDemo.adapter;

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
import com.queen.rxjava2withretrofitdemo.RealmEntityTest.RealmDoubanMovieSubject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liukun on 2017/4/10.
 */

public class RealmDoubanMovieAdapter extends BaseAdapter {
    private ArrayList<RealmDoubanMovieSubject> mDoubanMovieSubject;
    private Context mContext;
    private LayoutInflater inflater;

    private DoubanMovieItemClickListener mItemClickListener;

    public interface DoubanMovieItemClickListener{
        void onDoubanMovieItemClick(RealmDoubanMovieSubject doubanMovieSubject);
    }

    public RealmDoubanMovieAdapter(Context context, ArrayList<RealmDoubanMovieSubject> doubanMovieSubject,
                                   DoubanMovieItemClickListener itemClickListener) {
        mDoubanMovieSubject = doubanMovieSubject;
        mContext = context;
        inflater = LayoutInflater.from(context);
        mItemClickListener = itemClickListener;
    }

    @Override
    public int getCount() {
        if (mDoubanMovieSubject == null) {
            return 0;
        }

        return mDoubanMovieSubject.size();
    }

    @Override
    public RealmDoubanMovieSubject getItem(int position) {
        return mDoubanMovieSubject.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_douban_movie, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final RealmDoubanMovieSubject doubanMovieSubject = getItem(position);
        holder.itemDoubanMovieDirectorsTV.setText("导演：" + doubanMovieSubject.getDirectorNames());
        holder.itemDoubanMovieCastsTV.setText("主演：" + doubanMovieSubject.getCastsNames());
        holder.itemDoubanMovieRatingTV.setText(String.valueOf(doubanMovieSubject.getRating().getAverage()));
        holder.itemDoubanMovieWatchedTV.setText(doubanMovieSubject.getCollect_count() + "人看过");
        holder.itemDoubanMovieTitleTV.setText(doubanMovieSubject.getTitle());

        Glide.with(mContext).load(doubanMovieSubject.getImages().getLarge()).into(holder.itemDoubanMovieAvatarIV);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onDoubanMovieItemClick(doubanMovieSubject);
                }
            }
        });

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
