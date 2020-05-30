package com.example.jetpackdemo.day11_image_lang

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.jetpackdemo.R

class Main16Activity : AppCompatActivity() {


    private var navController: NavController? = null

    override fun onSupportNavigateUp(): Boolean {
        navController?.navigateUp()
        return super.onSupportNavigateUp()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main16)
        navController = Navigation.findNavController(findViewById(R.id.fragment5))
        NavigationUI.setupActionBarWithNavController(this, navController!!) //设置返回按钮

    }
}
