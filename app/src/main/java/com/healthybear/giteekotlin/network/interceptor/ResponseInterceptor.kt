package com.healthybear.giteekotlin.com.healthybear.giteekotlin.network.interceptor

import android.content.Intent
import android.text.TextUtils
import com.healthybear.giteekotlin.GKApplication
import com.healthybear.giteekotlin.constants.AppConstants
import com.healthybear.giteekotlin.dataSource.mmkv.SessionStorage
import com.healthybear.giteekotlin.ui.login.LoginActivity
import com.healthybear.library.network.manager.TokenRefreshManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.Response

class ResponseInterceptor : Interceptor {
    private var isRefreshing = false
    private val refreshLock = Any()

    private val mSessionStorage = SessionStorage()

    
    companion object {
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val host = originalRequest.url.host
        val url = originalRequest.url.toString()
        val response = chain.proceed(originalRequest)

        if (response.code == 401 && url.contains("oauth/token")) {
            synchronized(refreshLock) {
                val giteeToken = mSessionStorage.getData(AppConstants.STORAGE_KEYS.TAG_GITEE_TOKEN)
                if (TextUtils.isEmpty(giteeToken)) {
                    val intent = Intent(GKApplication.context, LoginActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    GKApplication.context.startActivity(intent)
                } else {
                    val refreshToken = mSessionStorage.getData(AppConstants.STORAGE_KEYS.TAG_GITEE_REFRESH_TOKEN)
                    if (!TextUtils.isEmpty(refreshToken)) {

                        var newResponse: Response? = null
                        CoroutineScope(Dispatchers.IO).launch {
                            if (!isRefreshing) {
                                isRefreshing = true
                            }
                            try {
                                val giteeTokenRep = TokenRefreshManager.refreshToken(refreshToken!!)
                                mSessionStorage.setData(AppConstants.STORAGE_KEYS.TAG_GITEE_TOKEN, giteeTokenRep.accessToken)
                                mSessionStorage.setData(AppConstants.STORAGE_KEYS.TAG_GITEE_REFRESH_TOKEN, giteeTokenRep.refreshToken)
                                mSessionStorage.setData(AppConstants.STORAGE_KEYS.TAG_GITEE_SCOPE, giteeTokenRep.scope)

                                /*var newBuilder = originalRequest.newBuilder()
                                val originalToken =  originalRequest.url.queryParameter("access_token")
                                if (!TextUtils.isEmpty(originalToken)) {
                                    val newUrl = originalRequest.url.newBuilder()
                                        .setQueryParameter("access_token", giteeTokenRep.accessToken)
                                        .build()
                                    newBuilder.url(newUrl)
                                }
                                // 处理请求体中的 access_token
                                originalRequest.body?.let { originalBody ->
                                    if (originalBody.contentType()?.subtype?.equals("x-www-form-urlencoded", ignoreCase = true) == true) {
                                        // 假设是表单数据
                                        val formBodyBuilder = okhttp3.FormBody.Builder()
                                        for (i in 0 until originalBody.size.toInt()) {
                                            val name = originalBody.name(i)
                                            val value = if (name == "access_token") {
                                                giteeTokenRep.accessToken
                                            } else {
                                                originalBody.value(i)
                                            }
                                            formBodyBuilder.add(name, value)
                                        }
                                        newBuilder = newBuilder.post(formBodyBuilder.build())
                                    }
                                    // 若有其他类型的请求体（如 JSON，XML, form-data），可按需添加处理逻辑
                                }

                                val newRequest = newBuilder.build()
                                response.close()
                                newResponse = chain.proceed(newRequest)*/
                            } catch (e: Exception) {

                            } finally {
                                isRefreshing = false
                            }
                        }

                    }

                }


                if (!isRefreshing) {
                    isRefreshing = true
                    /*return try {
                        val refreshToken = mSessionStorage.getRefreshToken() ?: throw ApiException.RefreshTokenNotFoundException("Refresh token not found")
                val tokenService = tokenRefreshService ?: throw ApiException.RefreshTokenServiceNotFoundException("TokenRefreshService not initialized")
                val newToken = tokenService.refreshToken(refreshToken)
                        val newRequest = originalRequest.newBuilder()
                            .build()
                        response.close()
                        chain.proceed(newRequest)
                    } catch (e: Exception) {
                        SessionStorage.clearTokens()
                        throw ApiException.RefreshTokenFailedException("Token refresh failed: ${e.message}")
                    } finally {
                        isRefreshing = false
                    }*/


                } else {
                    // 等待其他请求完成token刷新
                }
            }
        }
        return response
    }
}

