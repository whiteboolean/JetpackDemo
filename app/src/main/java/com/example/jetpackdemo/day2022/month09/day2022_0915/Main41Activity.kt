package com.example.jetpackdemo.day2022.month09.day2022_0915

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.jetpackdemo.R
import com.example.jetpackdemo.databinding.ActivityMain41Binding
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class Main41Activity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMain41Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMain41Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main4)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAnchorView(R.id.fab)
                .setAction("Action", null).show()

//            lifecycleScope.launch {
//                Log.e("MAIN41ACTIVITY", "currentThread:${Thread.currentThread().name}")
//                delay(100)
//                Log.e("MAIN41ACTIVITY", "123")
//            }
//            Log.e("MAIN41ACTIVITY", "456")


//            runBlocking {
//                Log.e("MAIN41ACTIVITY", "currentThread:${Thread.currentThread().name}")
//                delay(100)
//                Log.e("MAIN41ACTIVITY", "123")
//            }
//            Log.e("MAIN41ACTIVITY", "456")

            val handler  = CoroutineExceptionHandler { coroutineContext, throwable ->
                print(throwable)
            }

            lifecycleScope.launch(handler) {
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

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main4)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}