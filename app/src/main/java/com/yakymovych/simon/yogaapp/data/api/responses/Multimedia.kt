package com.yakymovych.simon.yogaapp.data.api.responses

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "multimedia")
data class Multimedia(
    var caption: String,
    var copyright: String,
    var format: String,
    var height: Int,
    var subtype: String,
    var type: String,
    @PrimaryKey
    var url: String,
    var width: Int
)