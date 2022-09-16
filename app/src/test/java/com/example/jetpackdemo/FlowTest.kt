package com.example.jetpackdemo

import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

fun main() {

    runBlocking {
        flow {
            for (i in 0..10) {
                emit("$i")
            }
        }.onStart {
            println("star:$this")
        }.onEach {
            print("onEach -> it:$it")
        }.onCompletion {
            println("onCompletion:$this")
        }.collect {
            println(it)
        }
    }

}