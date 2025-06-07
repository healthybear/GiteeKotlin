package com.healthybear.giteekotlin.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.healthybear.giteekotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mViewModel: MainViewModel
    private lateinit var mDataBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mDataBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mDataBinding.root)
        mViewModel = ViewModelProvider(this, MainViewModelFactory())
            .get(MainViewModel::class.java)
        

    }
}
