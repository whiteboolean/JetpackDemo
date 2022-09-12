package com.example.jetpackdemo.day2022.month09.day2022_0908

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.youth.banner.util.LogUtils
import kotlinx.coroutines.*

class DataViewModel : ViewModel() {

    private suspend fun main(){
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            log("Throws an exception with message: ${throwable.message}")
        }

        log(1)
        viewModelScope.launch(exceptionHandler) {
            throw ArithmeticException("Hey!")
        }.join()
        log(2)
    }


    /*异常处理*/
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->

    }

    fun testAsync() {
        viewModelScope.launch(exceptionHandler) {
            val deferred = async {
                LogUtils.e("准备抛出异常")
                delay(1000)
                throw Exception("async 抛出了一个异常")
            }
            /*加个延时 主要是验证异常是不是在await的时候抛出*/
            delay(2000)
            try {
                deferred.await()
            } catch (e: Exception) {
                LogUtils.e("在 await 处捕获到 async的异常了")
            }
            LogUtils.e("后续代码继续执行")
        }
    }


}

fun <T> T.log(any:Any){
    Log.e(this.toString(),any.toString())
}