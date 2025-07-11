package com.healthybear.giteekotlin.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.healthybear.giteekotlin.R
import com.healthybear.giteekotlin.databinding.ActivityMainBinding
import com.healthybear.giteekotlin.ui.main.adapter.MainViewPagerAdapter
import com.healthybear.library.base.activity.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {


    private lateinit var mFragmentList: ArrayList<Fragment>
    private lateinit var mFmAdapter: MainViewPagerAdapter
    private val mViewModel: MainViewModel by viewModels()

    override val inflater: (inflater: LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun initView() {
        mFragmentList = mViewModel.getFragmentList() as ArrayList<Fragment>
        mFmAdapter = MainViewPagerAdapter(mActivityWR.get()!!, mFragmentList)
        mBinding.viewPager.adapter = mFmAdapter

        mBinding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                mBinding.bottomNavigation.menu.getItem(position).setChecked(true)
            }
        })

        mBinding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    mBinding.viewPager.currentItem = 0
                    true
                }
                R.id.nav_repositories -> {
                    mBinding.viewPager.currentItem = 1
                    true
                }
                R.id.nav_message -> {
                    mBinding.viewPager.currentItem = 2
                    true
                }
                R.id.nav_user -> {
                    mBinding.viewPager.currentItem = 3
                    true
                }
                else -> false
            }
        }
        mBinding.bottomNavigation.selectedItemId = R.id.nav_home
    }
}
