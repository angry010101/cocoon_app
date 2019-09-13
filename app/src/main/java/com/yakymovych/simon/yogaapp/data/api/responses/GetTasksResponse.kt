package com.yakymovych.simon.yogaapp.data.api.responses

import com.yakymovych.simon.yogaapp.data.Task
import com.yakymovych.simon.yogaapp.data.api.requests.Meta

data class GetTasksResponse(val meta: Meta,val tasks: List<Task>)