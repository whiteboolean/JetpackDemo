package com.example.jetpackdemo.day1203_2021_coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import com.example.jetpackdemo.R
import kotlinx.coroutines.*
import kotlin.concurrent.thread

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main34)
        //https://www.jianshu.com/p/a08a9bd4aeb5
        //CoroutineScope : 翻译过来就是携程范围
        //花括号里面的代码就是运行在协程内的代码
        //Dispatchers 提供了几个值可以指定

        //https://juejin.cn/post/7027331505336614948
        //启动携程不只有launch这一个方法，还有async等其他方法可以启动携程，只是launch是最常用的一个方法。
//        GlobalScope.launch {
//            delay(1000L)
//            Log.i(TAG, "111 Hello World")
//        }


//        Log.i(TAG, "Start")
//        GlobalScope.launch(Dispatchers.Main) {
//            delay(1000L)
//            Log.i(TAG, "HelloWorld")
//        }
//        Log.i(TAG, "End")




        //协程代码
//        println("Start1 ${Thread.currentThread().name}")
        GlobalScope.launch(Dispatchers.Main) {
           withContext(Dispatchers.IO){

           }
            soutIo()
            val b = async {
                println("当前线程2名称我：${Thread.currentThread().name}")
            }
            println("Hello World1 ${Thread.currentThread().name}")
        }
//        println("End1 ${Thread.currentThread().name}")


        //使用线程完成上面的操作
        //线程代码
//        println("Start2 ${Thread.currentThread().name}")
//        thread {
//            Thread.sleep(1000L)
//
//            println("Hello World2 ${Thread.currentThread().name}")
//        }
//        println("End2 ${Thread.currentThread().name}")
    }

    private suspend fun soutIo() {
         withContext(Dispatchers.IO){
            println("当前线程名称为:${Thread.currentThread().name}")
        }
    }
}