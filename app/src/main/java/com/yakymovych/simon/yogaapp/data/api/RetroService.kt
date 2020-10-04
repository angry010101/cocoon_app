package com.yakymovych.simon.yogaapp.data.api

import com.yakymovych.simon.yogaapp.data.api.responses.GithubUser
import com.yakymovych.simon.yogaapp.data.api.responses.GithubUserInfo
import io.reactivex.Single
import retrofit2.http.*

interface RetroService {
    companion object {
        const val GET_USERS = "/users"
        const val GET_USER = "/users/{username}"
    }

    @GET(GET_USERS)
    fun getUsers(@Query("since") id: Int): Single<List<GithubUser>>

    @GET(GET_USER)
    fun getUserInfo(@Path("username") username: String): Single<GithubUserInfo>
}