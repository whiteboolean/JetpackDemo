package com.example.jetpackdemo.day25_view_binding.kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpackdemo.databinding.ActivityMain25Binding
import kotlinx.android.synthetic.main.activity_main25.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMain25Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main25)
        binding = ActivityMain25Binding.inflate(layoutInflater)
        setContentView(binding.root)
        button32.text = "hahah"

    }
}