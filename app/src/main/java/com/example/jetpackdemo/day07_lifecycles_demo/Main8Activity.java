package com.example.jetpackdemo.day07_lifecycles_demo;

import android.os.Bundle;
import android.widget.Chronometer;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jetpackdemo.R;

public class Main8Activity extends AppCompatActivity {

    private static final String TAG = "Main8Activity";
    private Chronometer chronometer;
    private long elapsedTime;
    private long firstElapsedTime;
    private long secondElapsedTime;
    private MyChronometer myChronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        chronometer = findViewById(R.id.textView26);
//        chronometer.setBase(System.currentTimeMillis());//UNIX 时间 1970 1-1
//        chronometer.setBase(SystemClock.elapsedRealtime());//从程序启动开始的时间 和系统时间无关
        myChronometer = findViewById(R.id.my_chronometer);
        getLifecycle().addObserver(myChronometer);
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        elapsedTime = SystemClock.elapsedRealtime() - chronometer.getBase();
//        chronometer.stop();
//    }
//
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        chronometer.setBase(SystemClock.elapsedRealtime() - elapsedTime);
//        chronometer.start();
//    }
}
