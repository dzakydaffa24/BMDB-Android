package com.example.bmdb.network

import com.squareup.moshi.Json

data class Movie(
    val id: String,
    @Json(name = "title") val title: String,
    val image: String
)