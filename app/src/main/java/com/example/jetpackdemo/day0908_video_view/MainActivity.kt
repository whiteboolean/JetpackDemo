package com.example.jetpackdemo.day0908_video_view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.jetpackdemo.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main32.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main32)

        mainViewPager.apply {
            adapter = object : FragmentStateAdapter(this@MainActivity) {
                override fun getItemCount() = 3

                override fun createFragment(position: Int) = when (position) {
                    1 -> VideoFragment()
                    else -> FooFragment()
                }
            }

            setCurrentItem(1, false)
        }

        TabLayoutMediator(tabLayout, mainViewPager) { tab: TabLayout.Tab, position: Int ->
            tab.text = when (position) {
                1 -> "Video"
                else -> "Foo"
            }
        }.attach()
    }
}