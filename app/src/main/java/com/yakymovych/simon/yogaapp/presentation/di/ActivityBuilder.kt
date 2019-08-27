package com.yakymovych.simon.yogaapp.di


import com.yakymovych.simon.yogaapp.ui.MainActivity
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
