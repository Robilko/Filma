package com.example.filma._core.data.api.model

import com.google.gson.annotations.SerializedName

data class ResponseMovieDetailsDTO(

    val movieDetails: MovieDetailsDTO,

    @SerializedName("errorMessage")
    val errorMessage: String?
)