package com.healthybear.library.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import java.lang.ref.WeakReference

/**
 * @author: 郭健鸿
 * @Date: 2025-06-10 23:59
 * @Description:
 */
abstract class BaseActivity<DB : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var mActivityWR : WeakReference<AppCompatActivity>
    protected lateinit var mBinding: DB
    protected abstract val inflater: (inflater: LayoutInflater) -> DB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityWR = WeakReference(this)
        mBinding = inflater(layoutInflater)
        setContentView(mBinding.root)
    }
}