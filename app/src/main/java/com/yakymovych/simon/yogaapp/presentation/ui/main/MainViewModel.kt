package com.yakymovych.simon.yogaapp.presentation.ui.main

import androidx.lifecycle.MutableLiveData
import com.yakymovych.simon.yogaapp.data.api.responses.Result
import com.yakymovych.simon.yogaapp.data.repository.FavoritesDb
import com.yakymovych.simon.yogaapp.data.repository.Repository
import com.yakymovych.simon.yogaapp.presentation.MVVMApplication
import com.yakymovych.simon.yogaapp.presentation.ui.BaseViewModel
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject


class MainViewModel @Inject constructor(application: MVVMApplication,
                                        db: FavoritesDb,
                                        private val repository: Repository) : BaseViewModel(application) {
    var data = MutableLiveData<List<Result>>()
    var dataSaved = MutableLiveData<List<Result>>()

    var dao = db.favorites()

    init {
        requestCached()
    }

    fun requestCached() {
        Thread {
            dataSaved.postValue(dao.favorites())
        }.start()
    }

    fun requestData() {
        repository.getData()?.subscribeBy({
            data.value = emptyList()
        }, {
            data.value = it.results
        })
    }
}