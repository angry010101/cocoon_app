package com.yakymovych.simon.yogaapp.data.repository

import com.yakymovych.simon.yogaapp.data.api.RetroService
import com.yakymovych.simon.yogaapp.data.api.responses.TopNewsResponse
import com.yakymovych.simon.yogaapp.presentation.utils.SchedulerProvider
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Repository @Inject constructor(
	private val retroService: RetroService,
	private val schedulerProvider: SchedulerProvider
) : BaseRepository() {


	fun getData(): Single<TopNewsResponse>? {
		return retroService.getData()
			.compose(schedulerProvider.getSchedulersForSingle())
	}
}