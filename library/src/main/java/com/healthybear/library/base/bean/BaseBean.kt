package com.healthybear.library.base.bean

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

open class BaseBean {
    /**
     * 重写toString方法，对象直接打印json串
     */
    override fun toString(): String {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val adapter = moshi.adapter(this.javaClass)
        return adapter.toJson(this)
    }
}
