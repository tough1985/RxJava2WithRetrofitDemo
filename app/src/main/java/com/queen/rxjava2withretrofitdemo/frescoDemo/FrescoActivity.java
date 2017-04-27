package com.queen.rxjava2withretrofitdemo.frescoDemo;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.GifRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.queen.rxjava2withretrofitdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liukun on 2017/4/11.
 */

public class FrescoActivity extends AppCompatActivity {

    @BindView(R.id.fresco_IV)
    SimpleDraweeView frescoIV;
    @BindView(R.id.glide_IV)
    ImageView glideIV;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fresco);
        ButterKnife.bind(this);

//        Uri uri = Uri.parse("https://img.gank.tv/feed/ecda22ccf05fc24537d9af1ebdadb760@1e_2o_0l_130h_278w_90q.gif");
//        Uri uri = Uri.parse("https://img.gank.tv/feed/0793bb0c00a38f38a54193a4f0e2c244@1e_2o_0l_612h_1080w_90q.gif");
        Uri uri = Uri.parse("https://imgim.gank.tv/chat/fccd0f5a5fac5efd4042f9fc45ad866b");


        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .build();
        frescoIV.setController(controller);

        RequestManager manager = Glide.with(this);
        GifRequestBuilder builder = manager.load(uri).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE);

        builder.dontAnimate().dontTransform().into(glideIV);

    }
}
