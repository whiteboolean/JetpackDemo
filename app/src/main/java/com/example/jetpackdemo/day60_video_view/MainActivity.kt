package com.example.jetpackdemo.day60_video_view

import android.media.PlaybackParams
import android.os.Bundle
import android.util.Log
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.jetpackdemo.R
import kotlinx.android.synthetic.main.activity_main27.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main27)
//        val videoPath = "android.resource://$packageName/${R.raw.video1}"
        val videoPath = "http://vfx.mtime.cn/Video/2019/02/04/mp4/190204084208765161.mp4"
        videoView.setVideoPath(videoPath)
        videoView.apply {
            setOnPreparedListener {

                progressBar.max = it.duration
                it.seekTo(1)
                it.playbackParams = PlaybackParams().apply {
                    speed = 2f
                }
            }
            setMediaController(MediaController(this@MainActivity))
//            start()
        }
        Log.e("TAG","当前线程:currentThread1:${Thread.currentThread().name}")
        lifecycleScope.launch {
      Log.e("TAG","当前线程:currentThread2:${Thread.currentThread().name}")
            while (true){
                if (videoView.isPlaying){
                    Log.e("TAG","当前线程:currentThread3:${Thread.currentThread().name}")
                    progressBar.progress = videoView.currentPosition
                }
                delay(500)
            }
        }

    }
}