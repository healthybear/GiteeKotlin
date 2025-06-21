package com.healthybear.library.network.response

/**
 * @author: 郭健鸿
 * @Date: 2025-06-15 11:54
 * @Description:
 */
sealed class ApiResponse<out T> {
    object Loading : ApiResponse<Nothing>()
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val message: String, val exception: Throwable?) : ApiResponse<Nothing>()
}
