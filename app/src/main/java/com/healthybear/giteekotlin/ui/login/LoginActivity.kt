package com.healthybear.giteekotlin.ui.login

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import androidx.lifecycle.ViewModelProvider
import com.healthybear.giteekotlin.databinding.ActivityLoginBinding
import com.healthybear.library.base.activity.BaseActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.healthybear.giteekotlin.R
import com.healthybear.library.network.response.ApiResponse
import com.kongzue.dialogx.dialogs.TipDialog
import com.kongzue.dialogx.dialogs.WaitDialog
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    override val inflater: (inflater: LayoutInflater) -> ActivityLoginBinding
        get() = ActivityLoginBinding::inflate

    private lateinit var viewModel: LoginViewModel

    override fun initView() {
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        // 初始化默认显示密码登录
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, PwdFragment.newInstance())
            .commit()

        setupStateObserver()
    }

    private fun setupStateObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loginResult
                    .filter { !it.hasBeenHandled() } // 过滤已处理状态
                    .collect { state ->
                        when (state) {
                            is ApiResponse.Loading -> WaitDialog.show(state.message)
                            is ApiResponse.Success -> {
                                TipDialog.show("登录成功", WaitDialog.TYPE.SUCCESS)
                                // 返回之前的页面
                                val targetClass = intent.getStringExtra("target_class")
                                if (!targetClass.isNullOrEmpty()) {
                                    val targetIntent = Intent(mActivityWR.get(), Class.forName(targetClass))
                                    startActivity(targetIntent)
                                }
                                setResult(Activity.RESULT_OK)
                                finish()
                            }
                            is ApiResponse.Error -> {
                                TipDialog.show(state.message, WaitDialog.TYPE.ERROR)
                            }
                            else -> {
                                // 处理其他状态
                            }
                        }
                        // 更新状态
                        viewModel.updateLoginResultState()
                    }
            }
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}