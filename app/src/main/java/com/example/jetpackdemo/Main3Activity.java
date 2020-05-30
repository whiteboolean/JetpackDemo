package com.example.jetpackdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.jetpackdemo.day01_viewmodel.MyViewModel;

/**
 * 引入liveData数据生命管理类
 */
public class Main3Activity extends AppCompatActivity {
    MyViewModel myViewModel;
    ViewModelProvider.AndroidViewModelFactory factory ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        factory = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication());
        myViewModel = factory.create(MyViewModel.class);
        MutableLiveData<Integer> likedNumber = myViewModel.getLikedNumber();
        final TextView textView = findViewById(R.id.textView5);
        likedNumber.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                textView.setText(String.valueOf(integer));
            }
        });

        findViewById(R.id.button11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              myViewModel.setLikedNumber(1);
            }
        });

        findViewById(R.id.button12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            myViewModel.setLikedNumber(-1);
            }
        });


    }
}
