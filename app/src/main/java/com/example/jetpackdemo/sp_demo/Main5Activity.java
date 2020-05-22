package com.example.jetpackdemo.sp_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.jetpackdemo.R;

public class Main5Activity extends AppCompatActivity {

    private static final String TAG = "Main5Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

//        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences preferences = getSharedPreferences("my_data",Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();

        edit.putInt("NUMBER",100);
        edit.apply();//非同步提交操作

        int x = preferences.getInt("NUMBER",1);

        System.out.println("x = " + x);

    }
}
