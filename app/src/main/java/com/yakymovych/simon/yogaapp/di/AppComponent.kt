package com.yakymovych.simon.yogaapp.di

import com.yakymovych.simon.yogaapp.MVVMApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(AndroidSupportInjectionModule::class, AppModule::class, ActivityBuilder::class,FragmentBuilder::class))
interface AppComponent : AndroidInjector<MVVMApplication> {

    @Component.Builder
    abstract class Builder: AndroidInjector.Builder<MVVMApplication>() {
    }
}