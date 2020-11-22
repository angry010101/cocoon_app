package com.yakymovych.simon.yogaapp.data.api

import com.yakymovych.simon.yogaapp.data.api.responses.TopNewsResponse
import io.reactivex.Single
import retrofit2.http.*

interface RetroService {
    companion object {
        const val GET_USERS = "svc/topstories/v2/arts.json?api-key=cdR5nUMbAM2vC4893e8P3MGRhaAarvHq"
    }

    @GET(GET_USERS)
    fun getData(): Single<TopNewsResponse>

}