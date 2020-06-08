package com.example.jetpackdemo.day14_my_test_demo_viewpager1.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.jetpackdemo.R
import com.example.jetpackdemo.day14_my_test_demo_viewpager1.PagerAdapter
import kotlinx.android.synthetic.main.fragment_blank.*
import java.util.*

class BlankFragment : Fragment() {


    private val mTitleList = ArrayList<String>(4)
    private val mFragments = ArrayList<Fragment>(4)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initFragmentList()
        val adapter = PagerAdapter(childFragmentManager,mTitleList,mFragments)
        vp_gank.offscreenPageLimit = 3
        vp_gank.adapter = adapter
        tab_gank.setupWithViewPager(vp_gank)//tabLayout和viewpager进行关联


    }

    private fun initFragmentList() {
        mTitleList.clear()
        mTitleList.add("玩安卓")
        mTitleList.add("发现")
        mTitleList.add("体系")
        mTitleList.add("导航")
        mFragments.add(BlankFragment4())
        mFragments.add(BlankFragment4())
        mFragments.add(BlankFragment4())
        mFragments.add(BlankFragment4())
    }

}