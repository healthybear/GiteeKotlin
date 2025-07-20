package com.healthybear.library.network.manager

import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.LogUtils
import com.healthybear.giteekotlin.dataSource.mmkv.SessionStorage
import com.healthybear.giteekotlin.network.response.GiteeToken
import com.healthybear.library.network.constant.BASE_URL
import com.healthybear.library.network.error.ApiException
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.POST
import retrofit2.http.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.http.Headers
import java.util.concurrent.TimeUnit

object TokenRefreshManager {

    private val mRetrofit: Retrofit

    init {
        mRetrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(initOkHttpClient())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    private fun initOkHttpClient() : OkHttpClient {
        val build = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)

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

        return build.build()
    }


    private val refreshService = mRetrofit.create(RefreshTokenService::class.java)

    suspend fun refreshToken(refreshToken: String): GiteeToken {
        val response = refreshService.refreshToken(
            refreshToken = refreshToken,
            grantType = "refresh_token"
        )
        return response
    }

    interface RefreshTokenService {
        @POST("oauth/token")
        @Headers("Content-Type: application/x-www-form-urlencoded")
        suspend fun refreshToken(
            @Query("refresh_token") refreshToken: String,
            @Query("grant_type") grantType: String = "refresh_token"
        ): GiteeToken
    }
}