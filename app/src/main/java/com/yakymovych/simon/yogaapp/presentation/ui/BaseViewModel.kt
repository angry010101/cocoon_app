package com.yakymovych.simon.yogaapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel() : ViewModel(){
    var toastMessage = MutableLiveData<String>()


}