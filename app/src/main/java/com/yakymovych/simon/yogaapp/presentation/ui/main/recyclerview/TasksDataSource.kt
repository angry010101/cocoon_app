package com.yakymovych.simon.yogaapp.ui.main.recyclerview


import androidx.paging.PageKeyedDataSource
import com.yakymovych.simon.yogaapp.data.api.RetroService
import com.yakymovych.simon.yogaapp.data.api.responses.GithubUser
import io.reactivex.rxkotlin.subscribeBy


class TasksDataSource(private val retroService: RetroService)
        : PageKeyedDataSource<Int, GithubUser>() {

    private var items: MutableList<GithubUser> = ArrayList()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, GithubUser>) {

        retroService.getUsers(0).subscribeBy(
                onSuccess = {
                    this@TasksDataSource.items.addAll(it)
                    callback.onResult(it,0 ,it[it.size-1].id)
                },
                onError = { throwable ->
                    callback.onResult(arrayListOf(),0,0)
                })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, GithubUser>) {
        retroService.getUsers(params.key).subscribeBy(
                onSuccess = {
                    this@TasksDataSource.items.addAll(it)
                    callback.onResult(it,it[it.size-1].id)
                },
                onError = { throwable ->
                    callback.onResult(arrayListOf(),params.key)
                })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, GithubUser>) {

    }
}