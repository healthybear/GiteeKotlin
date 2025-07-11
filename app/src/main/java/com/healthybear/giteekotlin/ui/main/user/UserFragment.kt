package com.healthybear.giteekotlin.ui.main.user

import android.content.Intent
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.unit.IntRect
import com.healthybear.giteekotlin.databinding.FragmentUserBinding
import com.healthybear.giteekotlin.ui.login.LoginActivity
import com.healthybear.library.base.fragment.BaseFragment

class UserFragment : BaseFragment<FragmentUserBinding>() {

    companion object {
        fun newInstance() = UserFragment()
    }

    override val inflater: (LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> FragmentUserBinding
        get() = FragmentUserBinding::inflate

    private val viewModel: UserViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        mBinding.btnLogin.setOnClickListener {
            startActivity(Intent(mActivityWR.get(), LoginActivity::class.java))
        }
    }
}