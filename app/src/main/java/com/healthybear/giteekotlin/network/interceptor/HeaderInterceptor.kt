package com.healthybear.giteekotlin.com.healthybear.giteekotlin.network.interceptor

import com.blankj.utilcode.util.LogUtils
import com.healthybear.giteekotlin.com.healthybear.giteekotlin.network.manager.CookiesManager
import com.healthybear.library.network.constant.ARTICLE_WEBSITE
import com.healthybear.library.network.constant.COIN_WEBSITE
import com.healthybear.library.network.constant.COLLECTION_WEBSITE
import com.healthybear.library.network.constant.KEY_COOKIE
import com.healthybear.library.network.constant.NOT_COLLECTION_WEBSITE
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author: 郭健鸿
 * @Date: 2025-06-15 12:34
 * @Description:
 */
class HeaderInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newBuilder = request.newBuilder()
        newBuilder.addHeader("Content-type", "application/json; charset=utf-8")

        val host = request.url.host
        val url = request.url.toString()

        //给有需要的接口添加Cookies
        if (!host.isNullOrEmpty()  && (url.contains(COLLECTION_WEBSITE)
                    || url.contains(NOT_COLLECTION_WEBSITE)
                    || url.contains(ARTICLE_WEBSITE)
                    || url.contains(COIN_WEBSITE))) {
            val cookies = CookiesManager.getCookies()
            LogUtils.e("HeaderInterceptor:cookies:$cookies")
            if (!cookies.isNullOrEmpty()) {
                newBuilder.addHeader(KEY_COOKIE, cookies)
            }
        }
        return chain.proceed(newBuilder.build())
    }
}