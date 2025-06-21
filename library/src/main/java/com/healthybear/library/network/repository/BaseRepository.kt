package com.healthybear.library.network.repository

import com.healthybear.library.network.response.ApiResponse
import retrofit2.Response

/**
 * @author: 郭健鸿
 * @Date: 2025-06-15 11:58
 * @Description:
 */
open class BaseRepository {
    suspend fun <T> requestResponse(requestCall: suspend () -> Response<T>): ApiResponse<T> {

        val response = requestCall()
        if (response.isSuccessful) {
            return ApiResponse.Success(response.body()!!)
        } else {
            return ApiResponse.Error("httpCode:${response.code()} httpMessage:${response.message()}", null)
        }
    }
}