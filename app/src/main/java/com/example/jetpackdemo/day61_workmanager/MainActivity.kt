package com.example.jetpackdemo.day61_workmanager

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import com.example.jetpackdemo.R
import kotlinx.android.synthetic.main.activity_main31.*

/**
 *
 * 使用workManager Api可以轻松地调度即使在应用退出或设备重启时
 * 仍运行的可延迟异步任务
 * 用统一的方法解决绝大部分后人哀问题
 *
 */

const val INPUT_DATA_KEY = "input_data_key"
const val OUTPUT_DATA_KEY = "output_data_key"
const val WORK_A_NAME = "work_a_name"
const val WORK_B_NAME = "work_b_name"
const val SHARED_PREFERENCES_NAME = "shp_name"
const val WORK_A_DATA_KEY = "work A key"
const val WORK_B_DATA_KEY = "work B key"

class MainActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {

    private val worker = WorkManager.getInstance(this);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main31)
        getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE).registerOnSharedPreferenceChangeListener(this)

        button42.setOnClickListener {
            val workRequestA = createWork(WORK_A_NAME)
            val workRequestB = createWork(WORK_B_NAME)

            worker.beginWith(workRequestA)
                    .then(workRequestB)
                    .enqueue()

//            worker.getWorkInfoByIdLiveData(workRequestA.id).observe(this, Observer {
////                Log.e(TAG,it.outputData.getString(OUTPUT_DATA_KEY))
//
//                if (it.state == WorkInfo.State.SUCCEEDED){
//                    Log.d(TAG, "onCreate: ${it.outputData.getString(OUTPUT_DATA_KEY)}")
//                }
//            })
        }

    }

    private fun updateView() {

        val sp = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        textView43.text = sp.getInt(WORK_A_NAME, 0).toString()
        textView42.text = sp.getInt(WORK_B_NAME, 0).toString()
    }


    private fun createWork(name: String): OneTimeWorkRequest {
        return OneTimeWorkRequestBuilder<Worker>()
                .setConstraints(
                        Constraints.Builder()
                                .setRequiredNetworkType(NetworkType.CONNECTED)
                                .build())
                .setInputData(workDataOf(INPUT_DATA_KEY to name))
                .build()
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        updateView()
    }


}