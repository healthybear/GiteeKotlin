package com.healthybear.giteekotlin.ui.main.message

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.healthybear.giteekotlin.R
import com.healthybear.giteekotlin.databinding.FragmentMessageBinding
import com.healthybear.library.base.fragment.BaseFragment

class MessageFragment : BaseFragment<FragmentMessageBinding>() {

    companion object {
        fun newInstance() = MessageFragment()
    }
    override val inflater: (LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> FragmentMessageBinding
        get() = FragmentMessageBinding::inflate

    private val viewModel: MessageViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}