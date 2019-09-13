package com.yakymovych.simon.yogaapp.presentation.di

import com.yakymovych.simon.yogaapp.presentation.ui.main.MainFragment
import com.yakymovych.simon.yogaapp.presentation.ui.main.MainViewModelModule
import com.yakymovych.simon.yogaapp.presentation.ui.login.LoginFragment
import com.yakymovych.simon.yogaapp.presentation.ui.login.LoginViewModelModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector(modules = arrayOf(LoginViewModelModule::class))
    abstract fun bindLoginFragment(): LoginFragment


    @ContributesAndroidInjector(modules = arrayOf(MainViewModelModule::class))
    abstract fun bindMainViewModel(): MainFragment
}