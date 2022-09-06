package com.example.jetpackdemo.day2022_0905

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bumptech.glide.Glide
import com.example.jetpackdemo.R
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main19.*
import kotlinx.android.synthetic.main.activity_main37.*

class MainActivity : AppCompatActivity() {

    private val strArray = arrayOf("关注", "推荐", "视频", "直播", "图片", "段子", "精华", "热门")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main37)
//
        Glide.with(this)
            .load("https://wx2.sinaimg.cn/mw690/002Po4pSly1grt79wku06j61jk0rskjl02.jpg").into(iv_image)
//            这种用法当fragment不会有内存泄露 虽然fragment很多 但是每次都是用的RecyclerFragment.newInstance()
        view_pager2.adapter = object :FragmentStateAdapter(this){
            override fun getItemCount(): Int = strArray.size

            override fun createFragment(position: Int): Fragment {
                return ItemFragment.newInstance(3)
            }
        }

        TabLayoutMediator(tab_layout,view_pager2){tab,position->
            tab.text = strArray[position]
        }.attach()
    }



}