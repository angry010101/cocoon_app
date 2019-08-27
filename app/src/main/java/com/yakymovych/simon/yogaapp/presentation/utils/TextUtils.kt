package com.yakymovych.simon.yogaapp.utils

import java.text.SimpleDateFormat
import java.util.*

object TextUtils{
    fun longToStringDate(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("yyyy.MM.dd HH:mm")
        return format.format(date)
    }
}