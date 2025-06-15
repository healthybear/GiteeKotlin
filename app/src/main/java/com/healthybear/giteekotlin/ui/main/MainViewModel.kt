package com.healthybear.giteekotlin.ui.main

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.healthybear.giteekotlin.data.MainRepository
import com.healthybear.giteekotlin.ui.main.home.HomeFragment
import com.healthybear.giteekotlin.ui.main.message.MessageFragment
import com.healthybear.giteekotlin.ui.main.repository.RepositoryFragment
import com.healthybear.giteekotlin.ui.main.user.UserFragment
import java.util.Arrays

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {
    fun getFragmentList(): List<Fragment> {
        return ArrayList(
            Arrays.asList(
                HomeFragment.newInstance(), RepositoryFragment.newInstance(),
            MessageFragment.newInstance(), UserFragment.newInstance()))
    }


}