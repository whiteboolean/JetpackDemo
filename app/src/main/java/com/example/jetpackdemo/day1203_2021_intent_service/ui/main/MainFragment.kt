package com.example.jetpackdemo.day1203_2021_intent_service.ui.main

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.lottie.L
import com.example.jetpackdemo.R
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.coroutines.delay
import kotlin.concurrent.thread

private const val TAG = "MainFragment"
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel

//        val intentService = Intent(context,IntentServiceDemo::class.java)
//        activity?.startService(intentService)

        val handler = MyHandler()
        thread {
//            Looper.prepare()
            handler.sendEmptyMessage(123)
//            handler.sendMessageAtFrontOfQueue(Message.obtain(handler,123))
//            println("hahahhahah")
//            Looper.loop()

        }

        var a:String? = null
        val lock = Object()
        Thread {
            synchronized(lock){
                a = "134"
                println("第一段代码")
                Thread.sleep(3000)
                lock.notifyAll()
            }
        }.start()


        // If the thread has been started, wait until the looper has been created.
        synchronized(lock) {
            while (a==null) {
                try {
                    println("第2段代码")
                    lock.wait()
                } catch (e: InterruptedException) {
                }
            }
        }

        println("第3段代码")
        println("a：$a")


//        val service1 = Intent(activity,ServiceDemo::class.java)
//        activity?.startService(service1)
//
//        message.setOnClickListener {
//            Log.d(TAG, "onViewCreated: " + "为什么不打印日志")
//            activity?.startService(service1)
//        }
//
//        button44.setOnClickListener {
//            activity?.stopService(service1)
//        }

//        button45.setOnClickListener {
//            activity?.bindser
//        }
    }

    inner class  MyHandler(looper:Looper? = null) : Handler(){
        override fun handleMessage(msg: Message) {
            println("${Thread.currentThread().name}")
            super.handleMessage(msg)
        }
    }

}