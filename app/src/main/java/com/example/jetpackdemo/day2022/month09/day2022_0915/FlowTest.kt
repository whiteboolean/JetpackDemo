package com.example.jetpackdemo.day2022.month09.day2022_0915

import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

fun main() {

    MainScope().launch() {
        flow {
            for (i in 0..10) {
                delay(100)
                emit("$i")
//                        if (i==3) throw RuntimeException("HAHAHA  死啦")
            }
        }.onStart {
            Log.e("MAINaCTIVITY", "onStart:${this.toString()}")
        }.onEach {
            Log.e("MAINaCTIVITY", "onEach:${it.toString()}")
        }.onCompletion {
            Log.e("MAINaCTIVITY", "onCompletion:${this.toString()}")
        }.collect {
            print(it)
        }
    }

}