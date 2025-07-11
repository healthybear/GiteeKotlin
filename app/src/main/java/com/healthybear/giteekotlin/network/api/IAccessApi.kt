package com.healthybear.giteekotlin.network.api

import com.healthybear.giteekotlin.network.response.GiteeToken
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.HEAD
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * @author: 郭健鸿
 * @Date: 2025-06-15 12:56
 * @Description:
 */
interface IAccessApi {

    /**
     * access请求
     */
    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("oauth/token")
    suspend fun getAccessTokenByPwd(
        @Field("grant_type") grantType: String = "password",
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("scope") scope: String = "projects user_info issues notes"
    ): Response<GiteeToken>
}