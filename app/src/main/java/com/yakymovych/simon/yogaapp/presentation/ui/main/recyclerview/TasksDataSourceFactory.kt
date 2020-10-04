package com.yakymovych.simon.yogaapp.presentation.ui.main.recyclerview

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.github.ajalt.timberkt.Timber
import com.yakymovych.simon.yogaapp.data.api.RetroService
import com.yakymovych.simon.yogaapp.data.api.responses.GithubUser
import com.yakymovych.simon.yogaapp.ui.main.recyclerview.TasksDataSource
import javax.inject.Inject

class TasksDataSourceFactory
@Inject constructor(private var retroService: RetroService) : DataSource.Factory<Int, GithubUser>() {

    val dataSource = MutableLiveData<DataSource<Int,GithubUser>>()

    override fun create(): DataSource<Int, GithubUser> {
        Timber.d { "CREATING DATASOURCE" }
        val ds = TasksDataSource(retroService)
        dataSource.postValue(ds)
        return ds
    }
}