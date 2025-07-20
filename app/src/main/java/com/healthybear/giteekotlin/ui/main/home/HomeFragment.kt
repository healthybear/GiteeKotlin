package com.healthybear.giteekotlin.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.SnackbarUtils
import com.healthybear.giteekotlin.databinding.FragmentHomeBinding
import com.healthybear.library.base.fragment.BaseFragment
import com.healthybear.library.network.response.ApiResponse
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val viewModel: HomeViewModel by viewModels()

    companion object {
        fun newInstance() = HomeFragment()
    }

    override val inflater: (LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupStateObserver()
        mBinding.clickTest.setOnClickListener {
            viewModel.searchResponse("android")
        }
    }

    private fun setupStateObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.mResponses.collect {
                    LogUtils.iTag("main",  it.toString())
                    when (it) {
                        is ApiResponse.Error -> {
                            LogUtils.eTag("main error",  it.message)
                        }
                        is ApiResponse.Success -> {
                            LogUtils.iTag("main success",  it.data.toString())
                        }
                        else -> {
                            LogUtils.iTag("main else ",  it.toString())
                        }
                    }
                }
            }
        }
    }
}