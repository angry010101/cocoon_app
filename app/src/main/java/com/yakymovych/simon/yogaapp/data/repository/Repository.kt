package com.yakymovych.simon.yogaapp.data.repository

import com.yakymovych.simon.yogaapp.data.api.RetroService
import com.yakymovych.simon.yogaapp.data.api.requests.LoginOrRegisterRequest
import com.yakymovych.simon.yogaapp.data.api.responses.LoginOrRegisterResponse
import com.yakymovych.simon.yogaapp.presentation.di.AuthInterceptor
import com.yakymovych.simon.yogaapp.presentation.utils.SchedulerProvider
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Repository @Inject constructor(val retroService: RetroService,
                                     val schedulerProvider: SchedulerProvider,
                                     val authInterceptor: AuthInterceptor) : BaseAuthRepository(authInterceptor){


    fun login(email: String, pass: String): Single<LoginOrRegisterResponse>{
        return retroService.login(LoginOrRegisterRequest(email,pass))
                .doAfterSuccess { token = it.token ?: token }
                .compose(schedulerProvider.getSchedulersForSingle())
    }



    fun register(email: String, pass: String): Single<LoginOrRegisterResponse>{
        return retroService.register(LoginOrRegisterRequest(email,pass))
                .doAfterSuccess { token = it.token ?: token }
                .compose(schedulerProvider.getSchedulersForSingle())
    }
}