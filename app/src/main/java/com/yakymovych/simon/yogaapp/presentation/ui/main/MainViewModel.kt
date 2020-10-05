package com.yakymovych.simon.yogaapp.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.yakymovych.simon.yogaapp.data.api.responses.GithubUser
import com.yakymovych.simon.yogaapp.data.api.responses.GithubUserInfo
import com.yakymovych.simon.yogaapp.presentation.ui.BaseViewModel

import com.yakymovych.simon.yogaapp.data.repository.Repository
import com.yakymovych.simon.yogaapp.presentation.ui.main.recyclerview.TasksDataSourceFactory
import com.yakymovych.simon.yogaapp.ui.main.recyclerview.TasksDataSource
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber
import javax.inject.Inject


class MainViewModel @Inject constructor(var tasksFactory: TasksDataSourceFactory,
                                        private val repository: Repository): BaseViewModel() {
    var tasks: LiveData<PagedList<GithubUser>>

    var isResfreshing = MutableLiveData<Boolean>()
    var pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(10)
            .setPageSize(LIMIT).build()

    var userData = MutableLiveData<GithubUserInfo>()

    var isLoadingData = MediatorLiveData<Boolean>().apply {
        value = false
    }

    companion object {
        var LIMIT = 15
    }

    init  {
        tasks = LivePagedListBuilder<Int, GithubUser>(tasksFactory, pagedListConfig).
                setInitialLoadKey(0).
                build()

        isLoadingData.addSource(tasksFactory.dataSource){
            it?.let {
                isLoadingData.addSource((it as TasksDataSource).isLoading){
                    isLoadingData.value = it
                }
            }
        }
    }

    var refreshListener = SwipeRefreshLayout.OnRefreshListener {
          this.isResfreshing.value = true
          refreshTasks()
    }

    fun refreshTasks(){
        tasksFactory.dataSource.value?.invalidate()
    }

    fun requestUser(name: String){
        repository.getInfoTx(name)?.subscribeBy({
            userData.value = null
        }, {
            userData.value = it
        })
    }
}