package com.example.jetpackdemo.day25_view_binding.java;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jetpackdemo.databinding.ActivityMain24Binding;

/**
 *
 * ViewBinding 是2020年4月份新出来的 是databinding的轻量版
 */

public class MainActivity extends AppCompatActivity {

    private ActivityMain24Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main24);
        binding = ActivityMain24Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.button30.setText("哈哈哈");
    }
}