package com.example.jetpackdemo.day1027_custom_view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackdemo.R
import kotlinx.android.synthetic.main.activity_main33.*

private const val TAG = "MyFragmentDialog"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main33)
//        lifecycle.addObserver(button44)
        val fragmentDialog = MyFragmentDialog()

        btn.setOnClickListener {
            fragmentDialog.show(supportFragmentManager, "fragment_dialog")
        }

//         ViewModelProvider(this).get()
//
//        lifecycle.addObserver()

//        val cardView = fragmentDialog.view?.findViewById<CardView>(R.id.cd)
////        Log.d(TAG, "onCreate: ")
////        val recyclerView1 = fragmentDialog.getRecycleView()
//        cardView?.setOnClickListener {
//            Toast.makeText(this, "你好", Toast.LENGTH_SHORT).show()
//        }


    }
}