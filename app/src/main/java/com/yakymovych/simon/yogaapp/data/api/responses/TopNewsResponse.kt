package com.yakymovych.simon.yogaapp.data.api.responses

data class TopNewsResponse(
    var copyright: String,
    var last_updated: String,
    var num_results: Int,
    var results: List<Result>,
    var section: String,
    var status: String
)