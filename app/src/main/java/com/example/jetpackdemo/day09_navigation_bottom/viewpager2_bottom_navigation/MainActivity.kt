package com.example.jetpackdemo.day09_navigation_bottom.viewpager2_bottom_navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.jetpackdemo.R
import kotlinx.android.synthetic.main.activity_main21.*

/**
 * https://www.jianshu.com/p/5993e8037d4d
 */
 class MainActivity : AppCompatActivity() {

    private val favoritesTabIndex = 0
    private val schedulesTabIndex = 1
    private val mineTabIndex = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main21)

        val fragments = getFragments()

        vp.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int): Fragment {
                return fragments[position]
            }

            override fun getItemCount(): Int {
                return fragments.size
            }
        }
        //禁用左右滑动切换页签
//        vp.isUserInputEnabled = false

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.item1 -> {
                    vp.setCurrentItem(favoritesTabIndex, false)
                }
                R.id.item2 -> {
                    vp.setCurrentItem(schedulesTabIndex, false)
                }
                R.id.item3 -> {
                    vp.setCurrentItem(mineTabIndex, false)
                }
            }
            true
        }
    }

    private fun getFragments(): ArrayList<Fragment> {
        val fragments = ArrayList<Fragment>(3)

        val favoritesFragment = BaseFragment()
        var bundle = Bundle()
        bundle.putString("title", "getString(R.string.favorites)")
        favoritesFragment.arguments = bundle

        val schedulesFragment = BaseFragment()
        bundle = Bundle()
        bundle.putString("title", "getString(R.string.schedules)")
        schedulesFragment.arguments = bundle

        val mineFragment = BaseFragment()
        bundle = Bundle()
        bundle.putString("title", "getString(R.string.mine)")
        mineFragment.arguments = bundle

        fragments.add(favoritesFragment)
        fragments.add(schedulesFragment)
        fragments.add(mineFragment)
        return fragments
    }
}