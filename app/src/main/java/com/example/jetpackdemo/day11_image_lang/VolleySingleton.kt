package com.example.jetpackdemo.day11_image_lang

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class VolleySingleton private constructor(context: Context) {

    companion object {
        private var INSTANCE: VolleySingleton? = null
        fun getInstance(context: Context): VolleySingleton? {
            if (INSTANCE===null){
                synchronized(this){
                    INSTANCE = VolleySingleton(context);
                }
            }
            return INSTANCE
        }

        fun getInstance2(context: Context) =
                INSTANCE ?: synchronized(this) {
            VolleySingleton(context).also {
                INSTANCE = it
            }
        }


    }

    val request: RequestQueue by lazy {
        Volley.newRequestQueue(context)
    }

}