package com.healthybear.library.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import java.lang.ref.WeakReference

abstract class BaseFragment<DB : ViewDataBinding> : Fragment() {

    protected lateinit var mActivityWR : WeakReference<Activity>
    protected lateinit var mBinding: DB
    protected abstract val inflater: (LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> DB


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivityWR = WeakReference(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = inflater(layoutInflater, container, false)
        return mBinding.root
    }
}