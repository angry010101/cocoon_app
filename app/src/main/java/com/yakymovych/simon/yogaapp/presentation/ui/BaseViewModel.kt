package com.yakymovych.simon.yogaapp.presentation.ui

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yakymovych.simon.yogaapp.presentation.MVVMApplication

abstract class BaseViewModel(application: Application) : AndroidViewModel(application){
    var toastMessage = MutableLiveData<String>()

    var networkStatus = MutableLiveData<Boolean>()

    init {
        initNetwork()
    }

    private fun initNetwork() {
        val connectivityManager = (this.getApplication<MVVMApplication>().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
        connectivityManager?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                it.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
                    override fun onAvailable(network: Network) {
                        networkStatus.postValue(true)
                    }
                    override fun onLost(network: Network?) {
                        networkStatus.postValue(false)
                    }
                })
            }
            else {
                //handle logic for 23 API and below
            }
        }
    }
}