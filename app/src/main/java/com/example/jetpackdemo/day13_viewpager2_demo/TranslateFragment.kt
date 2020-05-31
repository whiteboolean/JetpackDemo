package com.example.jetpackdemo.day13_viewpager2_demo

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.jetpackdemo.R
import kotlinx.android.synthetic.main.fragment_translate.*
import kotlin.random.Random


class TranslateFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_translate, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        imageView13.setOnClickListener {
            ObjectAnimator.ofFloat(it,"translationX",it.translationX + Random.nextInt(-100,100)).start()
        }
    }


}