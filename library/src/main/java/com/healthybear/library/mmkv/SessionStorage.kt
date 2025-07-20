package com.healthybear.giteekotlin.dataSource.mmkv

import android.annotation.SuppressLint
import android.content.Context
import com.blankj.utilcode.util.AppUtils
import com.tencent.mmkv.MMKV
import com.tencent.mmkv.MMKVLogLevel

@SuppressLint("ApplySharedPref")
open class SessionStorage() {


    private val mmkv by lazy {
        MMKV.mmkvWithID(SECURE_STORAGE, MMKV.MULTI_PROCESS_MODE, "YourEncryptionKey").apply {
            MMKV.setLogLevel(if (AppUtils.isAppDebug()) MMKVLogLevel.LevelDebug else MMKVLogLevel.LevelNone)
        }
    }

    companion object {

        const val SECURE_STORAGE = "secure_storage"
        private const val TIMESTAMP_SUFFIX = "_ts"
        private const val EXPIRATION_SUFFIX = "_exp"
    }

    /**
     * 存储数据
     */
    fun setData(key: String, value: String) {
        putDataInternal(key, value, 0L)
    }

    /**
     * 按小时存储（自动转换为秒）
     * @param hours 过期小时数 (1-24)
     */
    fun setDataForHours(key: String, value: String, hours: Int) {
        require(hours in 1..24) { "Hours must be between 1-24" }
        putDataInternal(key, value, hours * 3600L)
    }

    /**
     * 按天数存储（自动转换为秒）
     * @param days 过期天数 (1-365)
     */
    fun setDataForDays(key: String, value: String, days: Int) {
        require(days in 1..365) { "Days must be between 1-365" }
        putDataInternal(key, value, days * 86400L)
    }

    private fun putDataInternal(key: String, value: String, expireSeconds: Long) {
        mmkv.encode(key, value)
        mmkv.encode("${key}${TIMESTAMP_SUFFIX}", System.currentTimeMillis())
        if (expireSeconds > 0) {
            mmkv.encode("${key}${EXPIRATION_SUFFIX}", expireSeconds)
        }
    }

    /**
     * 获取加密数据（自动处理过期）
     */
    fun getData(key: String): String? {
        val expireDuration = mmkv.decodeLong("${key}${EXPIRATION_SUFFIX}", 0)
        val timestamp = mmkv.decodeLong("${key}${TIMESTAMP_SUFFIX}", 0)

        return when {
            expireDuration == 0L -> mmkv.decodeString(key)
            System.currentTimeMillis() - timestamp > expireDuration * 1000 -> {
                removeSecureData(key)
                null
            }
            else -> mmkv.decodeString(key)
        }
    }

    /**
     * 移除加密数据及相关时间戳
     */
    fun removeSecureData(key: String) {
        mmkv.removeValueForKey(key)
        mmkv.removeValueForKey("${key}${TIMESTAMP_SUFFIX}")
        mmkv.removeValueForKey("${key}${EXPIRATION_SUFFIX}")
    }
}