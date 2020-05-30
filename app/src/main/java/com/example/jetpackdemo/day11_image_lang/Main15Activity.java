package com.example.jetpackdemo.day11_image_lang;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.jetpackdemo.R;

import java.util.Random;

public class Main15Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main15);

        SwipeRefreshLayout layout = findViewById(R.id.srf);

        ImageView imageView = findViewById(R.id.imageView12);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url2 = "http://5b0988e595225.cdn.sohucs.com/images/20180724/fca3846b34e742deb4fb7da827953f9a.jpeg";
        String url1 = "https://cdn.pixabay.com/photo/2020/05/17/06/45/butterfly-5180306_1280.jpg";

        layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Random random = new Random();
                String url = random.nextBoolean()?url2:url1;
//                setRefresh(url,imageView,layout);
            }
        });


    }

//
//    private void setRefresh(String url,ImageView imageView,SwipeRefreshLayout layout){
//        Glide.with(this)
//                .load(url)
//                .placeholder(R.mipmap.ic_launcher)
//                .listener(new RequestListener<String, GlideDrawable>() {
//                    @Override
//                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
//                        if (layout.isRefreshing()){
//                            layout.setRefreshing(false);
//                        }
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                        if (layout.isRefreshing()){
//                            layout.setRefreshing(false);
//                        }
//                        return false;
//                    }
//                }).into(imageView);
//    }
}
