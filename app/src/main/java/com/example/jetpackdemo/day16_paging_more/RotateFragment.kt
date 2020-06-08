package com.example.jetpackdemo.day16_paging_more

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.jetpackdemo.R
import kotlinx.android.synthetic.main.fragment_rotate.*


class RotateFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_rotate, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        imageView13.setOnClickListener {
            ObjectAnimator.ofFloat(it,"rotation",it.rotation+30f).start()
        }


    }


}