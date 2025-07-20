package com.healthybear.giteekotlin.com.healthybear.giteekotlin.network.manager

import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.NetworkUtils
import com.healthybear.giteekotlin.com.healthybear.giteekotlin.network.interceptor.CookiesInterceptor
import com.healthybear.giteekotlin.com.healthybear.giteekotlin.network.interceptor.HeaderInterceptor
import com.healthybear.giteekotlin.com.healthybear.giteekotlin.network.interceptor.ResponseInterceptor
import com.healthybear.giteekotlin.com.healthybear.giteekotlin.network.interceptor.TokenInterceptor
import com.healthybear.library.network.constant.BASE_URL
import com.healthybear.library.network.error.ApiException
import com.healthybear.library.network.error.CusError
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author: 郭健鸿
 * @Date: 2025-06-15 12:28
 * @Description:
 */
object HttpManager {

    private val mRetrofit: Retrofit

    init {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
           .build()

        mRetrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(initOkHttpClient())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    /**
     * 获取 apiService
     */
    fun <T> create(apiService: Class<T>): T {
        return mRetrofit.create(apiService)
    }

    private fun initOkHttpClient(): OkHttpClient {
        val build = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
        // 添加参数拦截器
        val interceptors = mutableListOf<Interceptor>()
        build.addInterceptor(CookiesInterceptor())
        build.addInterceptor(HeaderInterceptor())
        build.addInterceptor(TokenInterceptor())
        //日志拦截器
        val logInterceptor = HttpLoggingInterceptor { message ->
            LogUtils.dTag("logInterceptor", message)
        }.also {
            when(AppUtils.isAppDebug()) {
                true -> it.level = HttpLoggingInterceptor.Level.HEADERS
                else -> it.level = HttpLoggingInterceptor.Level.BASIC
            }
        }
        build.addInterceptor(logInterceptor)
        //网络状态拦截
        build.addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                if (NetworkUtils.isConnected()) {
                    val request = chain.request()
                    return chain.proceed(request)
                } else {
                    throw ApiException.NoNetworkException(CusError.NETWORD_ERROR)
                }
            }
        })
        build.addInterceptor(ResponseInterceptor())
        return build.build()
    }
}