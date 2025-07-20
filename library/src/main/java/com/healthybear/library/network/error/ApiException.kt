package com.healthybear.library.network.error

import okio.IOException

/**
 * @author: 郭健鸿
 * @Date: 2025-06-15 12:03
 * @Description:
 */
open class ApiException: Exception {
    var errorCode: Int
    var errorMsg: String

    constructor(error: CusError, e: Throwable? = null) : super(e) {
        errorCode = error.code
        errorMsg = error.errMsg ?: "未知错误"
    }

    constructor(code: Int, msg: String, e: Throwable? = null) : super(e) {
        this.errorCode = code
        this.errorMsg = msg
    }

    class NoNetworkException : IOException {
        var errCode: Int
        var errMsg: String

        constructor(error: CusError, e: Throwable? = null) : super(e) {
            errCode = error.code
            errMsg = error.errMsg
        }
    }

    class TokenNotFoundException(message: String) : ApiException(-1, message)

    class RefreshTokenFailedException(message: String) : ApiException(-1, message)

}