package com.example.jetpackdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.jetpackdemo.databinding.ActivityMain3Binding;
import com.example.jetpackdemo.viewmodel.MyViewModel;

/**
 * 引入liveData数据生命管理类
 *
 * 通过dataBinding设置数据
 */
public class Main4Activity extends AppCompatActivity {
    MyViewModel myViewModel;
    ViewModelProvider.AndroidViewModelFactory factory;
    ActivityMain3Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main3);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main3);
        factory = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication());
        myViewModel = factory.create(MyViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setData(myViewModel);

//        MutableLiveData<Integer> likedNumber = myViewModel.getLikedNumber();
//        final TextView textView = findViewById(R.id.textView5);

//        likedNumber.observe(this, new Observer<Integer>() {
//            @Override
//            public void onChanged(Integer integer) {
//                binding.textView5.setText(String.valueOf(integer));
//            }
//        });

//        binding.button12.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myViewModel.setLikedNumber(1);
//            }
//        });
//
//        binding.button12.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myViewModel.setLikedNumber(-1);
//            }
//        });


    }
}
