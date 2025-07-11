package com.healthybear.giteekotlin.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.healthybear.giteekotlin.R
import com.healthybear.giteekotlin.databinding.FragmentPwdBinding
import com.healthybear.library.base.fragment.BaseFragment
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [PwdFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PwdFragment : BaseFragment<FragmentPwdBinding>() {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPwdBinding
        get() = FragmentPwdBinding::inflate

    companion object {
        fun newInstance() = PwdFragment()
    }

    private val mViewModel by lazy { LoginViewModel() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupStateObserver()
        mBinding.btnLogin.setOnClickListener {
            mViewModel.login(
                mBinding.etEmail.text.trim().toString(),
                mBinding.etPassword.text.trim().toString(),
                "",
                "")
        }
    }

    private fun setupStateObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mViewModel.loginResult.collect { response -> // 处理登录结果

                }
            }
        }
    }
}