package com.example.jetpackdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.junit.Test;

public class MyActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my3);
    }


    @Test
    public void test1(){
        System.out.println("测试方法");
    }
}
