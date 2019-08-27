package com.yakymovych.simon.yogaapp.di

import com.yakymovych.simon.yogaapp.MVVMApplication
import com.yakymovych.simon.yogaapp.ui.main.ViewModelFactoryModule
import com.yakymovych.simon.yogaapp.ui.login.LoginViewModelModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(AndroidSupportInjectionModule::class, AppModule::class, ActivityBuilder::class,FragmentBuilder::class, ViewModelFactoryModule::class, LoginViewModelModule::class))
interface AppComponent : AndroidInjector<MVVMApplication> {
    @Component.Builder
    abstract class Builder: AndroidInjector.Builder<MVVMApplication>() {
    }
}