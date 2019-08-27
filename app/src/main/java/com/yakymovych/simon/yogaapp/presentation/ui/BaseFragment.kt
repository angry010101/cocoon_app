package com.yakymovych.simon.yogaapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import dagger.android.support.DaggerFragment
import timber.log.Timber

abstract class BaseFragment : DaggerFragment() {
    abstract fun getBaseViewModel(): BaseViewModel

    override fun onStart() {
        super.onStart()
        observeBaseViewModel()
    }

    private fun observeBaseViewModel() {
        getBaseViewModel().toastMessage.observe(this, Observer {
            Timber.d("toastMessage changed $it")
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        })

    }
}