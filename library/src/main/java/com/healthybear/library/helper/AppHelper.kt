package com.healthybear.library.helper

import android.app.Application

/**
 * @author: 郭健鸿
 * @Date: 2025-06-15 12:43
 * @Description:
 */
object AppHelper {
    private lateinit var app: Application
    private var isDebug = false

    fun init(application: Application, isDebug: Boolean) {
        this.app = application
        this.isDebug = isDebug
    }

    /**
     * 获取全局应用
     */
    fun getApplication() = app

    /**
     * 是否为debug环境
     */
    fun isDebug() = isDebug
}