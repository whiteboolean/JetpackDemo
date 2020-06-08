package com.example.jetpackdemo.day15_new_livedata

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.jetpackdemo.R
import kotlinx.android.synthetic.main.activity_main23.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main23)

//        val viewModel = ViewModelProvider(this).get(MyViewModel::class.java) //旧的viewModel创建方式
        val viewModel1 by viewModels<MyViewModel>()
        viewModel1.text1.observe(this, Observer {
            textView38.text = it.toString()
        })

        button29.setOnClickListener {
            viewModel1.addOne()
        }
    }
}