package com.example.jetpackdemo.day14_my_test_demo_viewpager1

import android.text.Html
import android.util.SparseArray
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.jetpackdemo.day14_my_test_demo_viewpager1.fragment.BlankFragment
import com.example.jetpackdemo.day14_my_test_demo_viewpager1.fragment.BlankFragment2
import com.example.jetpackdemo.day14_my_test_demo_viewpager1.fragment.BlankFragment3

class PagerAdapter : FragmentStatePagerAdapter {

    private var mFragment: List<Fragment>? = null
    private var mTitleList: List<String>? = null
    private var mRegisteredFragments = SparseArray<Fragment>()


    constructor(fragmentManager: FragmentManager) :
            super(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)

    constructor(fragmentManager: FragmentManager, list: ArrayList<String>, fragmentList: ArrayList<Fragment>) :
            super(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        this.mTitleList = list
        this.mFragment = fragmentList
    }


    override fun getItem(position: Int): Fragment {
        return if (mFragment != null) {
            mFragment!![position]
        } else {
            when (position) {
                0 -> BlankFragment()
                1 -> BlankFragment2()
                2 -> BlankFragment3()
                else -> BlankFragment()
            }
        }
    }

    override fun getCount(): Int = mFragment?.size ?: 3


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val fragment = super.instantiateItem(container, position) as Fragment
        mRegisteredFragments.put(position, fragment)
        return fragment
    }


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        mRegisteredFragments.remove(position)
        super.destroyItem(container, position, `object`)
    }


    override fun getPageTitle(position: Int): CharSequence? {
        return if (mTitleList != null && position < mTitleList?.size!!) {
            Html.fromHtml(mTitleList!![position])
        } else {
            ""
        }
    }

}