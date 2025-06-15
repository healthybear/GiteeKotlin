package com.healthybear.giteekotlin.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.healthybear.giteekotlin.R
import com.healthybear.giteekotlin.databinding.ActivityMainBinding
import com.healthybear.giteekotlin.ui.main.adapter.MainViewPagerAdapter
import com.healthybear.library.base.BaseActivity

class MainActivity : BaseActivity() {

    private lateinit var mViewModel: MainViewModel
    private lateinit var mDataBinding: ActivityMainBinding

    private lateinit var mFragmentList: ArrayList<Fragment>
    private lateinit var mFmAdapter: MainViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mDataBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mDataBinding.root)
        mViewModel = ViewModelProvider(this, MainViewModelFactory())
            .get(MainViewModel::class.java)
        
        mFragmentList = mViewModel.getFragmentList() as ArrayList<Fragment>
        mFmAdapter = MainViewPagerAdapter(mActivityWR.get()!!, mFragmentList)
        mDataBinding.viewPager.adapter = mFmAdapter

        mDataBinding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                mDataBinding.bottomNavigation.menu.getItem(position).setChecked(true)
            }
        })

        mDataBinding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    mDataBinding.viewPager.currentItem = 0
                    true
                }
                R.id.nav_repositories -> {
                    mDataBinding.viewPager.currentItem = 1
                    true
                }
                R.id.nav_message -> {
                    mDataBinding.viewPager.currentItem = 2
                    true
                }
                R.id.nav_user -> {
                    mDataBinding.viewPager.currentItem = 3
                    true
                }
                else -> false
            }
        }
        mDataBinding.bottomNavigation.selectedItemId = R.id.nav_home
        
    }
}
