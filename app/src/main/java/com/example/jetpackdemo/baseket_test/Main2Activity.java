package com.example.jetpackdemo.baseket_test;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.jetpackdemo.R;
import com.example.jetpackdemo.databinding.ActivityMain2Binding;
import com.example.jetpackdemo.day02_sp_demo.Main5Activity;


/**
 * 篮球比赛计分器
 */
public class Main2Activity extends AppCompatActivity {
    private static final String TAG = "Main2Activity";
    private MyBasketViewModel myBasketViewModel;
    private ActivityMain2Binding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main2);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main2);
        myBasketViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MyBasketViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setData(myBasketViewModel);



        myBasketViewModel.changeData.observe(this,this::observer);
        binding.button35.setOnClickListener(this::button35Click);
        Log.e(TAG, "onCreate: ");
    }

    private void button35Click(View view) {
        startActivity(new Intent(this, Main5Activity.class));
    }

    private void observer(String s) {
        System.out.println("数据变化:"+s);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: ");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e(TAG, "onNewIntent: ");
    }

}
