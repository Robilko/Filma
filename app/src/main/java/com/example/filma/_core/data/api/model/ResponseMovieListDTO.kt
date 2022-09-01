package com.example.filma._core.data.api.model

import com.google.gson.annotations.SerializedName

data class ResponseMovieListDTO(
    @SerializedName("items")
    val movies: List<MovieDTO>,

    @SerializedName("errorMessage")
    val errorMessage: String?
)