package com.yakymovych.simon.yogaapp.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.yakymovych.simon.yogaapp.data.api.responses.GithubUser
import com.yakymovych.simon.yogaapp.data.api.responses.GithubUserInfo
import com.yakymovych.simon.yogaapp.presentation.ui.BaseViewModel

import com.yakymovych.simon.yogaapp.data.repository.Repository
import com.yakymovych.simon.yogaapp.presentation.ui.main.recyclerview.TasksDataSourceFactory
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

    companion object {
        var LIMIT = 15
    }

    init  {
        tasks = LivePagedListBuilder<Int, GithubUser>(tasksFactory, pagedListConfig).
                setInitialLoadKey(0).
                build()
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