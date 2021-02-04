package com.example.jetpackdemo.day0908_video_view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.jetpackdemo.R
import kotlinx.android.synthetic.main.fragment_video.*


class VideoFragment : Fragment() {

    private val videoUrls:List<String>  = listOf(
            "http://192.168.254.108:8080/v1.mp4",
            "http://192.168.254.108:8080/v2.mp4",
            "http://192.168.254.108:8080/v3.mp4",
            "http://192.168.254.108:8080/v4.mp4",
            "http://192.168.254.108:8080/v5.mp4",
            "http://192.168.254.108:8080/v6.mp4",
            "http://192.168.254.108:8080/v7.mp4",
            "http://192.168.254.108:8080/v8.mp4",
            "http://192.168.254.108:8080/v9.mp4"
    )


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_video, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        videoViewPager.apply {

            adapter  = object: FragmentStateAdapter(this@VideoFragment){
                override fun getItemCount() = videoUrls.size

                override fun createFragment(position: Int) =
                        PlayerFragment.newInstance(videoUrls[position],"null")

            }

            offscreenPageLimit = 3
        }
    }

}