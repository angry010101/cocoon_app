package com.yakymovych.simon.yogaapp.ui.main.recyclerview


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.github.ajalt.timberkt.Timber
import com.yakymovych.simon.yogaapp.data.api.RetroService
import com.yakymovych.simon.yogaapp.data.api.responses.GithubUser
import com.yakymovych.simon.yogaapp.data.repository.GithubDb
import com.yakymovych.simon.yogaapp.data.repository.GithubUserDao
import io.reactivex.rxkotlin.subscribeBy


class TasksDataSource(
        private val db: GithubDb,
        private val retroService: RetroService)
    : PageKeyedDataSource<Int, GithubUser>() {

    private var items: MutableList<GithubUser> = ArrayList()
    private var dao: GithubUserDao = db.users()

    var isLoading = MutableLiveData<Boolean>()
    var notifyEnd = MutableLiveData<Boolean>()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, GithubUser>) {
        isLoading.postValue(true)
        retroService.getUsers(0).subscribeBy(
                onSuccess = {
                    Timber.d { "SUCCESS NETWORK INIT" }
                    this@TasksDataSource.items.addAll(it)
                    callback.onResult(it, 0, it[it.size - 1].id)
                    dao.insert(it)
                    isLoading.postValue(false)
                },
                onError = { throwable ->
                    throwable.printStackTrace()
                    Timber.d { "ERROR NETWORK INIT" }
                    isLoading.postValue(false)
                    val data = dao.getAllUsers(0)
                    callback.onResult(data, 0, data.get(data.size - 1).id)
                })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, GithubUser>) {
        isLoading.postValue(true)
        retroService.getUsers(params.key).subscribeBy(
                onSuccess = {
                    Timber.d { "SUCCESS NETWORK AFTER" }
                    this@TasksDataSource.items.addAll(it)
                    callback.onResult(it, it[it.size - 1].id)
                    dao.insert(it)
                    isLoading.postValue(false)
                    if (it.size == 0) {
                        notifyEnd.postValue(true)
                    }
                },
                onError = { throwable ->
                    Timber.d { "ERROR NETWORK AFTER" }
                    isLoading.postValue(false)
                    val data = dao.getAllUsers(params.key + 1)
                    val next = if (data.isEmpty()) params.key + 1 else data.get(data.size - 1).id
                    callback.onResult(data, next)
                })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, GithubUser>) {

    }
}