package com.example.jetpackdemo.day02_sp_demo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jetpackdemo.R;
import com.example.jetpackdemo.baseket_test.Main2Activity;

public class Main5Activity extends AppCompatActivity {

    private static final String TAG = "Main5Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

//        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences preferences = getSharedPreferences("my_data", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();

        edit.putInt("NUMBER", 100);
        edit.apply();//非同步提交操作

        int x = preferences.getInt("NUMBER", 1);

        System.out.println("x = " + x);
        Log.e(TAG, "onCreate: ");

        findViewById(R.id.button36).setOnClickListener(this::buttonClick);
        findViewById(R.id.button37).setOnClickListener(this::buttonClick2);

    }

    private void buttonClick2(View view) {
        startActivity(new Intent(this, Main5Activity.class));
    }

    private void buttonClick(View view) {
        startActivity(new Intent(this, Main2Activity.class));
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
