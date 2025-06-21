package com.healthybear.library.network.error

import android.util.MalformedJsonException
import androidx.core.net.ParseException
import com.blankj.utilcode.util.ToastUtils
import com.squareup.moshi.JsonDataException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException

/**
 * @author: 郭健鸿
 * @Date: 2025-06-15 12:10
 * @Description: 统一错误处理工具类
 */
object ExceptionHandler {
    fun handleException(e: Throwable): ApiException {

        val ex: ApiException
        if (e is ApiException) {
            ex = ApiException(e.errorCode, e.errorMsg, e)
            if (ex.errorCode == CusError.UNLOGIN.code){
                //登录失效
            }
        } else if (e is ApiException.NoNetworkException) {
            ToastUtils.showShort("网络异常，请尝试刷新")
            ex = ApiException(CusError.NETWORD_ERROR, e)
        } else if (e is HttpException) {
            ex = when (e.code()) {
                CusError.UNAUTHORIZED.code -> ApiException(CusError.UNAUTHORIZED, e)
                CusError.FORBIDDEN.code -> ApiException(CusError.FORBIDDEN, e)
                CusError.NOT_FOUND.code -> ApiException(CusError.NOT_FOUND, e)
                CusError.REQUEST_TIMEOUT.code -> ApiException(CusError.REQUEST_TIMEOUT, e)
                CusError.GATEWAY_TIMEOUT.code -> ApiException(CusError.GATEWAY_TIMEOUT, e)
                CusError.INTERNAL_SERVER_ERROR.code -> ApiException(CusError.INTERNAL_SERVER_ERROR, e)
                CusError.BAD_GATEWAY.code -> ApiException(CusError.BAD_GATEWAY, e)
                CusError.SERVICE_UNAVAILABLE.code -> ApiException(CusError.SERVICE_UNAVAILABLE, e)
                else -> ApiException(e.code(), e.message(), e)
            }
        } else if (e is JsonDataException
            || e is JSONException
            || e is ParseException
            || e is MalformedJsonException
        ) {
            ex = ApiException(CusError.PARSE_ERROR, e)
        } else if (e is ConnectException) {
            ex = ApiException(CusError.NETWORD_ERROR, e)
        } else if (e is javax.net.ssl.SSLException) {
            ex = ApiException(CusError.SSL_ERROR, e)
        } else if (e is java.net.SocketException) {
            ex = ApiException(CusError.TIMEOUT_ERROR, e)
        } else if (e is java.net.SocketTimeoutException) {
            ex = ApiException(CusError.TIMEOUT_ERROR, e)
        } else if (e is java.net.UnknownHostException) {
            ex = ApiException(CusError.UNKNOW_HOST, e)
        } else {
            ex = if (!e.message.isNullOrEmpty()) ApiException(1000, e.message!!, e)
            else ApiException(CusError.UNKNOWN, e)
        }
        return ex
    }
}