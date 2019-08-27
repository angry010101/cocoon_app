package com.yakymovych.simon.yogaapp.ui.main.recyclerview
//
//import androidx.lifecycle.MutableLiveData
//import androidx.paging.DataSource
//import com.github.ajalt.timberkt.Timber
//import com.yakymovych.simon.yogaapp.data.api.RetroService
//import com.yakymovych.simon.yogaapp.data.Sort
//import com.yakymovych.simon.yogaapp.data.Task
//import javax.inject.Inject
//
//class TasksDataSourceFactory
//@Inject constructor(private var retroService: RetroService)
//    : DataSource.Factory<Int, Task>() {
//
//    var sort: Sort = Sort.ID
//    val dataSource = MutableLiveData<DataSource<Int,Task>>()
//
//    override fun create(): DataSource<Int, Task> {
//        Timber.d { "CREATING DATASOURCE" }
//        val ds = TasksDataSource(retroService)
//        ds.sortType = sort.toString() + " asc"
//        dataSource.postValue(ds)
//        return dsg
//    }
//}