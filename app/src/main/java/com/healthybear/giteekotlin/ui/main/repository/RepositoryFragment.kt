package com.healthybear.giteekotlin.ui.main.repository

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.healthybear.giteekotlin.R
import com.healthybear.giteekotlin.databinding.FragmentRepositoryBinding
import com.healthybear.library.base.BaseFragment

class RepositoryFragment : BaseFragment<FragmentRepositoryBinding>() {

    companion object {
        fun newInstance() = RepositoryFragment()
    }
    override val inflater: (LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> FragmentRepositoryBinding
        get() = FragmentRepositoryBinding::inflate

    private val viewModel: RepositoryViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}