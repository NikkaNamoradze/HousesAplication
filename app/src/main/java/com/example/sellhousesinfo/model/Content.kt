package com.example.sellhousesinfo.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


data class Content(
    val id: String,
    val descriptionKA: String,
    val titleKA: String,
    @field:Json(name = "publish_date")
    val publishDate: String,
    val cover: String,
)