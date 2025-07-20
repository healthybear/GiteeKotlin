package com.healthybear.library.base.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineExceptionHandler
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.blankj.utilcode.util.LogUtils
import com.healthybear.library.network.error.ApiException
import com.healthybear.library.R
import com.holo.architecture.widget.TransBehavior
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

/**
 * @author: 郭健鸿
 * @Date: 2025-06-10 23:59
 * @Description:
 */
abstract class BaseActivity<DB : ViewDataBinding> : AppCompatActivity() {
    protected val TAG = javaClass.simpleName

    private var loadingDialog: MaterialDialog? = null
    private var dialogJob: Job? = null

    protected lateinit var mActivityWR : WeakReference<AppCompatActivity>
    protected lateinit var mBinding: DB
    protected abstract val inflater: (inflater: LayoutInflater) -> DB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityWR = WeakReference(this)
        mBinding = inflater(layoutInflater)
        setContentView(mBinding.root)

        // 启用边缘到边缘模式  (沉浸式体验、统一视觉风格、适配全面屏设备)
        enableETE()

        initView()
    }


    /**
     * 处理token错误，如token不存在或刷新失败
     * 子类可重写此方法实现具体的跳转逻辑
     */
    open fun onTokenError() {
        // 默认实现为空，由子类重写
    }

    fun showLoading() {
        if (loadingDialog == null) {
            loadingDialog = MaterialDialog(mActivityWR.get()!!, TransBehavior)
                .customView(R.layout.dialog_loading)
                .cancelOnTouchOutside(false)
                .cancelable(false)
        }
        if (loadingDialog?.isShowing != true) {
            dialogJob?.cancel()
            dialogJob = lifecycleScope.launch {
                delay(1000)
                loadingDialog?.show()
                delay(5000)
                LogUtils.i(TAG, "超时，取消loading")
                hideLoading()
            }
        }
    }

    fun hideLoading() {
        dialogJob?.cancel()
        dialogJob = null
        if (isDestroyed || isFinishing) return
        loadingDialog?.dismiss()
    }

    /**
     * (沉浸式体验、统一视觉风格、适配全面屏设备)
     */
    private fun enableETE() {
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(mBinding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    abstract fun initView()

}