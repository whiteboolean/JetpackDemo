package com.example.jetpackdemo.day13_viewpager2_demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.jetpackdemo.R
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main19.*

/**
 * 最简单使用viewpager2的几个步骤
 * 1.在布局中声明viewpager2
 * 2.设置viewpager2.adapter
 * 3.设置layoutMediator进行关联
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main19)

        viewpager2.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = 3
            override fun createFragment(position: Int) = when (position) {
                0 -> ScaleFragment()
                1 -> RotateFragment()
                else -> TranslateFragment()
            }
        }

        TabLayoutMediator(tabLayout, viewpager2) { tab, position ->
                when(position){
                    0-> tab.text = "缩放"
                    1->tab.text ="旋转"
                    else -> tab.text = "平移"
                }
        }.attach()
    }
}