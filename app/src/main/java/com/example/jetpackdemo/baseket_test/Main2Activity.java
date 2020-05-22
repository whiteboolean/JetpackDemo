package com.example.jetpackdemo.baseket_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.os.PersistableBundle;

import com.example.jetpackdemo.R;
import com.example.jetpackdemo.databinding.ActivityMain2Binding;


/**
 * 篮球比赛计分器
 */
public class Main2Activity extends AppCompatActivity {

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
    }



}
