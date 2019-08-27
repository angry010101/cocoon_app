package com.yakymovych.simon.yogaapp.ui.main

import androidx.lifecycle.ViewModelProvider
import com.yakymovych.simon.yogaapp.di.DaggerViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: DaggerViewModelFactory): ViewModelProvider.Factory
}