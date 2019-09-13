package com.yakymovych.simon.yogaapp.data.repository

import com.yakymovych.simon.yogaapp.presentation.di.AuthInterceptor

abstract class BaseAuthRepository(private val authInterceptor: AuthInterceptor) : BaseRepository() {
    var token: String = ""
        set(value) {
            authInterceptor.sessionToken = value
            field = value
        }
}