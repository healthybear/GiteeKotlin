package com.healthybear.giteekotlin.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.material3.Snackbar
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.SnackbarUtils
import com.google.android.material.snackbar.Snackbar
import com.healthybear.giteekotlin.BuildConfig
import com.healthybear.giteekotlin.R
import com.healthybear.giteekotlin.databinding.FragmentPwdBinding
import com.healthybear.library.base.fragment.BaseFragment
import com.healthybear.library.network.response.ApiResponse
import com.kongzue.dialogx.DialogX
import com.kongzue.dialogx.dialogs.TipDialog
import com.kongzue.dialogx.dialogs.WaitDialog
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

    private lateinit var viewModel: LoginViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]

        mBinding.btnLogin.setOnClickListener {
            viewModel.login(
                mBinding.etEmail.text.toString().trim(),
                mBinding.etPassword.text.toString().trim(),
                BuildConfig.GITEE_CLIENT_ID,
                BuildConfig.GITEE_CLIENT_SECRET)
        }

        mBinding.tvSwitchLogin.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, OAuthFragment.newInstance())
                .addToBackStack(null)
                .commit()
        }
    }

}