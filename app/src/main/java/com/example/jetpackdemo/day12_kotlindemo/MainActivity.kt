package com.example.jetpackdemo.day12_kotlindemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackdemo.R
import kotlinx.android.synthetic.main.activity_main18.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main18)

        val viewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        viewModel.number.observe(this, Observer { textView32.text = it.toString() })
        button25.setOnClickListener {viewModel.modifyNumber(1)} ; button26.setOnClickListener { viewModel.modifyNumber(-1) }


    }
}