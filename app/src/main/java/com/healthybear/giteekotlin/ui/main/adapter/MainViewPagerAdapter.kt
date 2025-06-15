package com.healthybear.giteekotlin.ui.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * @author: 郭健鸿
 * @Date: 2025-06-08 23:39
 * @Description:
 */
class MainViewPagerAdapter(fragmentActivity: FragmentActivity, fragmentList: List<Fragment>): FragmentStateAdapter(fragmentActivity) {

    private val mFragmentList: List<Fragment> = fragmentList

    override fun getItemCount(): Int {
        return mFragmentList?.size ?: 0
    }

    override fun createFragment(position: Int): Fragment {
        return mFragmentList[position]
    }

}