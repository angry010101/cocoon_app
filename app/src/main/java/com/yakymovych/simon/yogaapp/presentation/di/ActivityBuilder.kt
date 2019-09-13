package com.yakymovych.simon.yogaapp.presentation.di


import com.yakymovych.simon.yogaapp.presentation.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Scope

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = arrayOf())
    abstract fun bindMainActivity(): MainActivity


}


@Scope
@Retention
annotation class FragmentScope



@Scope
@Retention
annotation class ActivityScope
