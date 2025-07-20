package com.healthybear.library.network.response

/**
 * @author: 郭健鸿
 * @Date: 2025-06-15 11:54
 * @Description:
 */
sealed class ApiResponse<out T> {
    private var hasBeenHandled = false

    fun hasBeenHandled() : Boolean {
        return hasBeenHandled
    }

    /**
     * 处理过的状态，返回自身
     */
    fun getMarkedAsUsedData() : ApiResponse<T> {
        if (!hasBeenHandled) {
            hasBeenHandled = true
        }
        return this
    }

    object Empty : ApiResponse<Nothing>()
    data class Loading(val message: String) : ApiResponse<Nothing>()
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val message: String, val exception: Throwable?) : ApiResponse<Nothing>()
}