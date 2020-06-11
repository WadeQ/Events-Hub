package com.wadektech.eventshub.models

import com.squareup.moshi.Json


data class Test (
        val id :String,
        @Json(name = "img_src")
        val imgUrl : String,
        val type : String,
        val price : Double
)