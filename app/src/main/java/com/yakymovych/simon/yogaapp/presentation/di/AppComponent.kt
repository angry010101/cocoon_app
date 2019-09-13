package com.yakymovych.simon.yogaapp.presentation.di

import com.yakymovych.simon.yogaapp.presentation.MVVMApplication
import com.yakymovych.simon.yogaapp.presentation.ui.main.ViewModelFactoryModule
import com.yakymovych.simon.yogaapp.presentation.ui.login.LoginViewModelModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(AndroidSupportInjectionModule::class, AppModule::class, ActivityBuilder::class, FragmentBuilder::class, ViewModelFactoryModule::class, LoginViewModelModule::class))
interface AppComponent : AndroidInjector<MVVMApplication> {
    @Component.Builder
    abstract class Builder: AndroidInjector.Builder<MVVMApplication>() {
    }
}