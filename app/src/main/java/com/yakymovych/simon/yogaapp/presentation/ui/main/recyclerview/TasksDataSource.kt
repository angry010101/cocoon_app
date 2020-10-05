package com.yakymovych.simon.yogaapp.ui.main.recyclerview


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
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

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, GithubUser>) {
        isLoading.postValue(true)
        retroService.getUsers(0).subscribeBy(
                onSuccess = {
                    this@TasksDataSource.items.addAll(it)
                    callback.onResult(it,0 ,it[it.size-1].id)
                    dao.insert(it)
                    isLoading.postValue(false)
                },
                onError = { throwable ->
                    isLoading.postValue(false)
                    callback.onResult(dao.getAllUsers(0),0,0)
                })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, GithubUser>) {
        isLoading.value = true
        retroService.getUsers(params.key).subscribeBy(
                onSuccess = {
                    this@TasksDataSource.items.addAll(it)
                    callback.onResult(it,it[it.size-1].id)
                    dao.insert(it)
                    isLoading.postValue(false)
                },
                onError = { throwable ->
                    isLoading.postValue(false)
                    callback.onResult(emptyList(),params.key)
                })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, GithubUser>) {

    }
}