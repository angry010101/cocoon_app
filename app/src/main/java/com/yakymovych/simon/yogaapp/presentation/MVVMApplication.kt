package com.yakymovych.simon.yogaapp.presentation

import android.content.Context
import com.github.ajalt.timberkt.Timber
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import androidx.multidex.MultiDex
import com.yakymovych.simon.yogaapp.BuildConfig
import com.yakymovych.simon.yogaapp.presentation.di.DaggerAppComponent


class MVVMApplication : DaggerApplication(){
    private val injection = DaggerAppComponent.builder().create(this)
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        injection

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
    override fun onCreate() {
        super.onCreate()


        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}

