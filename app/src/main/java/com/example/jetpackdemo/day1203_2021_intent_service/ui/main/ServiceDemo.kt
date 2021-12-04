package com.example.jetpackdemo.day1203_2021_intent_service.ui.main

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

private const val TAG = "ServiceDemo"
class ServiceDemo : Service() {


    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: ")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand: ")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(TAG, "onUnbind: ")
        return super.onUnbind(intent)
    }

    override fun onBind(intent: Intent?): IBinder?  {

        Log.d(TAG, "onBind: ")
        return null
    }

    override fun onStart(intent: Intent?, startId: Int) {
        Log.d(TAG, "onStart: ")
        super.onStart(intent, startId)
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: ")
        super.onDestroy()
    }
}