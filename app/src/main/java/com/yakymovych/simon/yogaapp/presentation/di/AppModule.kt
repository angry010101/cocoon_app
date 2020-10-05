package com.yakymovych.simon.yogaapp.presentation.di

import android.content.Context
import androidx.room.Room
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.yakymovych.simon.yogaapp.presentation.MVVMApplication
import com.yakymovych.simon.yogaapp.data.api.RetroService
import com.yakymovych.simon.yogaapp.data.repository.GithubDb
import com.yakymovych.simon.yogaapp.presentation.utils.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
open class AppModule {
	@Singleton
	@Provides
	fun provideContext(application: MVVMApplication): Context = application

	@Provides
	@Singleton
	open fun provideSchedulerProvider() =
		SchedulerProvider(Schedulers.io(), AndroidSchedulers.mainThread())

	@Provides
	@Singleton
	open fun provideDatabase(context: Context): GithubDb =
		Room.databaseBuilder(context, GithubDb::class.java, "database").build()

	@Provides
	@Singleton
	internal open fun provideRetroService(context: Context): RetroService {
		return if (false) { //debug
			throw NotImplementedError()
		} else {
			val dispatcher = Dispatcher().apply {
				maxRequests = 1
			}
			val client = OkHttpClient.Builder().dispatcher(dispatcher).build()
			Retrofit.Builder()
				.baseUrl("https://api.github.com/")
				.client(client)
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.build()
				.create(RetroService::class.java).apply {

				}
		}
	}
}