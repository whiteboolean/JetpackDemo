package com.example.jetpackdemo.day61_workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf

const val TAG = "Worker"

class Worker(context: Context, workerParameters: WorkerParameters) : Worker(context, workerParameters) {
    override fun doWork(): Result {

        val name = inputData.getString(INPUT_DATA_KEY)
//        Log.d(TAG, "doWork: started :$name:name")
        Thread.sleep(3000)
        val sp = applicationContext.getSharedPreferences(SHARED_PREFERENCES_NAME,Context.MODE_PRIVATE)
        var number = sp.getInt(name,0)
        sp.edit().putInt(name,++number).apply()
//        Log.d(TAG, "doWork: finished :$name name")
        return Result.success(workDataOf(OUTPUT_DATA_KEY to "out punt Key"))

    }

}