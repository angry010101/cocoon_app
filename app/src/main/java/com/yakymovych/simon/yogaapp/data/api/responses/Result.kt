package com.yakymovych.simon.yogaapp.data.api.responses

import androidx.room.*
import com.google.gson.annotations.SerializedName


//TODO add other serialized name attributes here
@Entity(tableName = "favorites")
data class Result(
        @PrimaryKey
        var url: String,
        @SerializedName("abstract")
        var abstractText: String? = null,
        var byline: String? = null,
        var created_date: String? = null,
        var item_type: String? = null,
        var kicker: String? = null,
        var material_type_facet: String? = null,
        @Ignore var multimedia: List<Multimedia> = emptyList(),
        var published_date: String? = null,
        var section: String? = null,
        var short_url: String? = null,
        var subsection: String? = null,
        var title: String? = null,
        var updated_date: String? = null,
        var uri: String? = null,
        var image: String? = null
){
        constructor():this("")
}