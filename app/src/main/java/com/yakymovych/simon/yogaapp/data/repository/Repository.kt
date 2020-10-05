package com.yakymovych.simon.yogaapp.data.repository

import com.yakymovych.simon.yogaapp.data.api.RetroService
import com.yakymovych.simon.yogaapp.data.api.responses.GithubUser
import com.yakymovych.simon.yogaapp.data.api.responses.GithubUserInfo
import com.yakymovych.simon.yogaapp.presentation.utils.SchedulerProvider
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Repository @Inject constructor(
	private val retroService: RetroService,
	private val schedulerProvider: SchedulerProvider
) : BaseRepository() {
	fun getData(id: Int): Single<List<GithubUser>>? {
		return retroService.getUsers(id)
			.compose(schedulerProvider.getSchedulersForSingle())
	}

	fun getInfoTx(username: String): Single<GithubUserInfo>? {
		return retroService.getUserInfo(username)
			.compose(schedulerProvider.getSchedulersForSingle())
	}
}