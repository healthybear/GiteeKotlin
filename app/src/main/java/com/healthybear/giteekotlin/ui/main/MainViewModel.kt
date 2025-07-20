package com.healthybear.giteekotlin.ui.main

import androidx.fragment.app.Fragment
import com.healthybear.giteekotlin.ui.main.home.HomeFragment
import com.healthybear.giteekotlin.ui.main.message.MessageFragment
import com.healthybear.giteekotlin.ui.main.repository.RepositoryFragment
import com.healthybear.giteekotlin.ui.main.user.UserFragment
import com.healthybear.library.base.viewModel.BaseViewModel
import java.util.Arrays

class MainViewModel() : BaseViewModel() {
    
    fun getFragmentList(): List<Fragment> {
        return ArrayList(
            Arrays.asList(
                HomeFragment.newInstance(),
                RepositoryFragment.newInstance(),
                MessageFragment.newInstance(),
                UserFragment.newInstance()))
    }


}