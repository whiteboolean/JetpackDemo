package com.example.jetpackdemo.day1027_custom_view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jetpackdemo.R
import kotlinx.android.synthetic.main.activity_main33.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main33)
        lifecycle.addObserver(button44)

    }
}