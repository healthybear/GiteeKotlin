package com.healthybear.library.base.viewModel

import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    data class Event<T>(private val content: T) {
        private var hasBeenHandled = false

        // 获取内容，如果已经处理过则返回null
        fun getContentIfNotHandled(): T? {
            return if (hasBeenHandled) {
                null
            } else {
                hasBeenHandled = true
                content
            }
        }

        // 强制获取内容，无论是否处理过
        fun peekContent(): T = content
    }
}