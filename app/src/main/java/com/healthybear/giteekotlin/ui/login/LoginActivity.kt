package com.healthybear.giteekotlin.ui.login

import android.view.LayoutInflater
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.healthybear.giteekotlin.R
import com.healthybear.giteekotlin.databinding.ActivityLoginBinding
import com.healthybear.library.base.activity.BaseActivity
import kotlinx.coroutines.launch

class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    override val inflater: (inflater: LayoutInflater) -> ActivityLoginBinding
        get() = ActivityLoginBinding::inflate

    override fun initView() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(mBinding.container.id, OAuthFragment.newInstance())
        fragmentTransaction.commit()
    }


}