package com.healthybear.library.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.lang.ref.WeakReference

/**
 * @author: 郭健鸿
 * @Date: 2025-06-10 23:59
 * @Description:
 */
open class BaseActivity : AppCompatActivity() {

    protected lateinit var mActivityWR : WeakReference<AppCompatActivity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityWR = WeakReference(this)
    }
}