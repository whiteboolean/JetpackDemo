package com.example.jetpackdemo.day14_my_test_demo_viewpager1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.jetpackdemo.R
import kotlinx.android.synthetic.main.activity_main20.*


/**
 * 使用viewpager1完成fragment嵌套viewpager
 *
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main20)
        val pagerAdapter = PagerAdapter(supportFragmentManager)
        viewpager.adapter = pagerAdapter
        viewpager.offscreenPageLimit = 2 //只允许缓存两条
        viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> setCurrentItem(0)
                    1 -> setCurrentItem(1)
                    2 -> setCurrentItem(2)
                    else -> {
                    }
                }
            }
        })
        setCurrentItem(0)
    }

    /**
     * 切换页面
     *
     * @param position 分类角标
     */
    private fun setCurrentItem(position: Int) {
        var isOne = false
        var isTwo = false
        var isThree = false
        when (position) {
            0 -> isOne = true
            1 -> isTwo = true
            2 -> isThree = true
            else -> isTwo = true
        }
        viewpager.currentItem = position
        iv_title_one.isSelected = isOne
        iv_title_two.isSelected = isTwo
        iv_title_three.isSelected = isThree
    }
}