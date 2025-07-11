package com.healthybear.giteekotlin

import android.app.Application
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.LogUtils
import com.healthybear.giteekotlin.BuildConfig
import com.tencent.bugly.crashreport.CrashReport

/**
 * @author: 郭健鸿
 * @Date: 2025-06-15 10:20
 * @Description:
 */
class GKApplication : Application() {

    companion object {
    }


    override fun onCreate() {
        super.onCreate()
        // 初始化Bugly
        CrashReport.initCrashReport(getApplicationContext(), BuildConfig.BUGLY_API_KEY, AppUtils.isAppDebug());
        // 初始化日志
//        LogUtils.getConfig().
    }
}