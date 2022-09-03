package com.example.jetpackdemo.day1203_2021_intent_service

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.MutableLiveData
import com.example.jetpackdemo.R
import com.example.jetpackdemo.day1203_2021_intent_service.ui.main.MainFragment
import io.reactivex.Observable
import kotlinx.android.synthetic.main.main_activity.*
import java.util.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {


    val liveData1 = MutableLiveData<String>()
    companion object{
        private const val TAG = "MainActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }



        liveData1.observe(this) {
            Log.e(TAG,"当前的值为：$it")
        }


//        liveData1.observe()
//        val btn  = findViewById<Button>(R.id.btn)

//        btn.setOnClickListener {
//            liveData1.value = ("123")
//            liveData1.value = ("123456")
//            liveData1.value = ("12354567")
//        }

//        thread {
//            liveData1.value ="1234355"
//        }
//        liveData1.value = ("12354567")

//        liveData1.postValue ("123")
//        thread {
//            Thread.sleep(1000)
//            liveData1.postValue ("123456")
//        }
//
//        thread {
//            Thread.sleep(2000)
//            liveData1.postValue  ("12354567")
//        }


    }

    override fun onStart() {
        super.onStart()
        liveData1.value = ("12345")
        liveData1.value = ("123456")

    }

    override fun onResume() {
        super.onResume()
//        liveData1.value = ("12345")
//        liveData1.value = ("123456")
    }
}