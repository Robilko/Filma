package com.example.filma._core.data.api.model

import com.google.gson.annotations.SerializedName

data class MovieDetailsDTO(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("originalTitle")
    val originalTitle: String,
    @SerializedName("fullTitle")
    val fullTitle: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("year")
    val year: String,
    @SerializedName("image")
    val imageUrl: String?,
    @SerializedName("releaseDate")
    val releaseDate: String,
    @SerializedName("runtimeMins")
    val runtimeMins: String,
    @SerializedName("runtimeStr")
    val runtimeStr: String,
    @SerializedName("plot")
    val description: String,
    @SerializedName("plotLocal")
    val descriptionLocal: String,
    @SerializedName("plotLocalIsRtl")
    val descriptionLocalIsRtl: Boolean,
    @SerializedName("directors")
    val directors: String,
    @SerializedName("actorList")
    val actorList: List<ActorDTO>,
    @SerializedName("genres")
    val genres: String,
    @SerializedName("imDbRating")
    val imDbRating: String?,
    @SerializedName("keywords")
    val keywords: String,
    @SerializedName("keywordList")
    val keywordList: List<String>,
    @SerializedName("similars")
    val similars: List<MovieDTO>
)

data class ActorDTO(
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("asCharacter")
    val asCharacter: String
)