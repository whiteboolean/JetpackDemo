package com.example.jetpackdemo.day1203_2021_intent_service

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jetpackdemo.R
import com.example.jetpackdemo.day1203_2021_intent_service.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}