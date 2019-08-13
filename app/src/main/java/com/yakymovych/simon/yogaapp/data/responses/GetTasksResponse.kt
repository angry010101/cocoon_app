package com.yakymovych.simon.yogaapp.data.responses

import com.yakymovych.simon.yogaapp.data.Task
import com.yakymovych.simon.yogaapp.data.requests.Meta

data class GetTasksResponse(val meta: Meta,val tasks: List<Task>)