package com.example.jetpackdemo.day13_viewpager2_demo

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.jetpackdemo.R
import kotlinx.android.synthetic.main.fragment_scale.*


class ScaleFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scale, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        imageView15.setOnClickListener {
            val scale = if (java.util.Random().nextBoolean()) 0.1f else -0.1f
            ObjectAnimator.ofFloat(it,"scaleX",it.scaleX+scale).start()
            ObjectAnimator.ofFloat(it,"scaleY",it.scaleY+scale).start()
        }
    }

}