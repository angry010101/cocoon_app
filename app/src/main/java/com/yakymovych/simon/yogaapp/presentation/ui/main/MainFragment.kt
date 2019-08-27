package com.yakymovych.simon.yogaapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.yakymovych.simon.yogaapp.R
import com.yakymovych.simon.yogaapp.di.DaggerViewModelFactory
import com.yakymovych.simon.yogaapp.ui.BaseFragment
import com.yakymovych.simon.yogaapp.ui.BaseViewModel
import javax.inject.Inject

class MainFragment: BaseFragment() {
    @Inject
    lateinit var viewModeFactory: DaggerViewModelFactory

    lateinit var mainViewModel: MainViewModel

    override fun getBaseViewModel(): BaseViewModel = mainViewModel

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        mainViewModel = ViewModelProvider(this, viewModeFactory).get(MainViewModel::class.java)
        val rootView = inflater.inflate(R.layout.activity_main2, container, false)
        return rootView
    }

}