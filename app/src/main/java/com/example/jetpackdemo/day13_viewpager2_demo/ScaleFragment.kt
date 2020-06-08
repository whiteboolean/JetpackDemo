package com.example.jetpackdemo.day13_viewpager2_demo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.jetpackdemo.R
import kotlinx.android.synthetic.main.fragment_scale.*
import kotlinx.coroutines.*


private const val TAG = "ScaleFragment"

class ScaleFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scale, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        imageView15.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main) {
                //这里执行完会自动切换回来
                Log.e(TAG, "开始执行")
                val a = async { runOnThread() }
                val b = async { runOnThread2() }
                val name = suspendingFunck(a.await(), b.await())
                println(name)
                Log.e(TAG, "执行结束")
//                withContext(Dispatchers.IO){
//                   Log.e(TAG,Thread.currentThread().name)
//                   sleep(5000)
//               }
//                //在切换
//                Log.e(TAG,Thread.currentThread().name)
//                withContext(Dispatchers.IO){
//                    Log.e(TAG,Thread.currentThread().name)
//                }
            }

//            val scale = if (java.util.Random().nextBoolean()) 0.1f else -0.1f
//            ObjectAnimator.ofFloat(it, "scaleX", it.scaleX + scale).start()
//            ObjectAnimator.ofFloat(it, "scaleY", it.scaleY + scale).start()
        }
    }

    private fun suspendingFunck(a: Int, b: Int): Any {
        return a.plus(b)
    }

    private suspend fun runOnThread2(): Int {
        withContext(Dispatchers.IO) {
            delay(2000)
        }
        return 3
    }

    private suspend fun runOnThread(): Int {
        withContext(Dispatchers.IO) {
            delay(4000)
        }
        return 2
    }

}