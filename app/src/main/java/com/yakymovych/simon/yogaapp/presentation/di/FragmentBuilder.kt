package com.yakymovych.simon.yogaapp.presentation.di

import com.yakymovych.simon.yogaapp.presentation.ui.main.DetailsFragment
import com.yakymovych.simon.yogaapp.presentation.ui.main.MainFragment
import com.yakymovych.simon.yogaapp.presentation.ui.main.MainViewModelModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {
    @ContributesAndroidInjector(modules = arrayOf(MainViewModelModule::class))
    abstract fun bindMainViewModel(): MainFragment

    @ContributesAndroidInjector(modules = arrayOf(MainViewModelModule::class))
    abstract fun bindDetailsViewModel(): DetailsFragment
}