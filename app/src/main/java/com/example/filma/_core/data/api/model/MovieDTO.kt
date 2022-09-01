package com.example.filma._core.data.api.model

import com.google.gson.annotations.SerializedName

data class MovieDTO(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("image")
    val image: String?,
    @SerializedName("imDbRating")
    val imDbRating: String
)