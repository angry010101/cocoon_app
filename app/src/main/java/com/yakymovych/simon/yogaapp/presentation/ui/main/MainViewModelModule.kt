package com.yakymovych.simon.yogaapp.ui.main

import androidx.lifecycle.ViewModel
import com.yakymovych.simon.yogaapp.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModule(myViewModel: MainViewModel): ViewModel
}
