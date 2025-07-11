package com.healthybear.library.base.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import java.lang.ref.WeakReference

abstract class BaseFragment<DB : ViewDataBinding> : Fragment() {
    protected val TAG = javaClass.simpleName

    //是否第一次加载
    protected var isFirst: Boolean = true

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isFirst = true
    }

    override fun onResume() {
        super.onResume()
        onVisible()
    }

    private fun onVisible() {
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED) && isFirst) {
            view?.postDelayed({
                lazyLoadData()
                isFirst = false
            }, 120)
        }
    }

    fun lazyLoadData() {}

}