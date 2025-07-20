package com.healthybear.giteekotlin.com.healthybear.giteekotlin.network.interceptor

import android.text.TextUtils
import com.healthybear.giteekotlin.constants.AppConstants
import com.healthybear.giteekotlin.dataSource.mmkv.SessionStorage
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor: Interceptor {

    private lateinit var mSessionStorage: SessionStorage
    init {
        mSessionStorage = SessionStorage()
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newBuilder = request.newBuilder()

        val host = request.url.host
        val url = request.url.toString()

        val originalToken =  request.url.queryParameter("access_token")
        if (TextUtils.isEmpty(originalToken)) {
            val newUrl = request.url.newBuilder()
                .setQueryParameter("access_token", mSessionStorage.getData(AppConstants.STORAGE_KEYS.TAG_GITEE_REFRESH_TOKEN))
                .build()
            newBuilder.url(newUrl)
        }
        return chain.proceed(newBuilder.build())
    }
}