package com.example.jetpackdemo.day16_paging_more

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.jetpackdemo.R
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main19.*
import org.greenrobot.eventbus.EventBus

private const val TAG = "MainActivity"

/**
 * 最简单使用viewpager2的几个步骤 -- 第一种简单写法
 * 1.在布局中声明viewpager2
 * 2.设置viewpager2.adapter
 * 3.设置layoutMediator进行关联
 *
 * 相关代码 ： https://www.jianshu.com/p/246e26a4d741
 */

class MainActivity : AppCompatActivity() {

    lateinit var viewPager: ViewPager2
    lateinit var fragment: FirstFragment

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main19)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fragment = FirstFragment()
        viewpager2.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = 3
            override fun createFragment(position: Int) = when (position) {
                0 -> ScaleFragment()
                1 -> fragment
                2 -> RotateFragment()
                else -> TranslateFragment()
            }
        }

        viewPager = findViewById(R.id.viewpager2)
        //viewpager2 和 tabLayout绑定使用方式
        TabLayoutMediator(tabLayout, viewpager2) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "图片画廊"
                }
                1 -> {
                    tab.text = "缩放"
                }
                2 -> tab.text = "旋转"
                else -> tab.text = "平移"
            }
        }.attach()
        viewpager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> {
                        viewPager.isUserInputEnabled = true
                        EventBus.getDefault().post(ScrollEvent(0))
//                        Log.e(TAG, "外部可滑动")
                    }
                    1 -> {
                        viewPager.isUserInputEnabled = false
                        EventBus.getDefault().post(ScrollEvent(1))
//                        Log.e(TAG, "外部不可滑动")
                    }
                    2 -> {
                        viewPager.isUserInputEnabled = true
                        EventBus.getDefault().post(ScrollEvent(2))
//                        Log.e(TAG, "外部可滑动")
                    }
                }

            }
        })
    }
}