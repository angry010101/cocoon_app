package com.yakymovych.simon.yogaapp.presentation.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel() : ViewModel(){
    var toastMessage = MutableLiveData<String>()


}