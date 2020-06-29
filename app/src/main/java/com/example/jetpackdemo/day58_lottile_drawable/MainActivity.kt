package com.example.jetpackdemo.day58_lottile_drawable

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import com.example.jetpackdemo.R
import kotlinx.android.synthetic.main.activity_main26.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main26)

        imageViewLottie.setOnClickListener {
            imageViewLottie.playAnimation()
        }

        motionLayout.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
            }

            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
                imageViewLottie.progress = p3
            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
            }

        })

        button41.setOnClickListener{
            startActivity(Intent(this,MainActivity2::class.java))
        }
    }
}