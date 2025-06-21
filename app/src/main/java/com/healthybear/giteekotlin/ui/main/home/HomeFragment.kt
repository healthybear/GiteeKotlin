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
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    companion object {
        fun newInstance() = HomeFragment()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO: Use the ViewModel

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.clickTest.setOnClickListener {
            viewModel.searchResponse("Test")
        }

        setupStateObserver()
        return binding.root
    }

    private fun setupStateObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.mResponses.collect {
                    LogUtils.iTag("main",  it.toString())
                    SnackbarUtils.with(binding.root).setMessage(it.toString()).show()
                }
            }
        }
    }
}