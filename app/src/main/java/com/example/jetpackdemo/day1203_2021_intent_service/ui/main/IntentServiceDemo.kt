package com.example.jetpackdemo.day1203_2021_intent_service.ui.main

import android.app.IntentService
import android.content.Intent
import android.os.SystemClock
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val TAG = "IntentServiceDemo"

class IntentServiceDemo(val name: String = "") : IntentService(name) {


    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onHandleIntent(intent: Intent?) {
        //在这里进行请求的操作
        Log.e(TAG, "进来了")
        repeat(100) {
            SystemClock.sleep(2000)
            Log.e(TAG, "IntentService跑起来了，咔咔跑")
            Log.e(TAG, "当前线程为${Thread.currentThread().name}")
            Log.e(TAG," ---- ")
        }
//        GlobalScope.launch {
//
//        }

        val thread = Thread()
        thread.start()
    }
}