package com.healthybear.giteekotlin

import android.app.Application
import com.healthybear.giteekotlin.com.healthybear.giteekotlin.network.interceptor.ResponseInterceptor
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.LogUtils
import com.kongzue.dialogx.DialogX
import com.tencent.bugly.crashreport.CrashReport
import com.tencent.mmkv.MMKV

/**
 * @author: 郭健鸿
 * @Date: 2025-06-15 10:20
 * @Description:
 */
class GKApplication : Application() {

    companion object {
        lateinit var context: GKApplication
            private set
    }


    override fun onCreate() {
        super.onCreate()
        context = this

        // 初始化Bugly
        CrashReport.initCrashReport(getApplicationContext(), BuildConfig.BUGLY_API_KEY, AppUtils.isAppDebug());
        // 初始化日志
        LogUtils.getConfig().setLogSwitch(AppUtils.isAppDebug())
        // 初始化MMKV
        MMKV.initialize(this)
        // 初始化DialogX
        DialogX.init(this)

    }
}