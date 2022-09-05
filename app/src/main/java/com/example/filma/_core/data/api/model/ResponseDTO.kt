package com.example.filma._core.data.api.model

import com.google.gson.annotations.SerializedName

data class ResponseDTO(
    @SerializedName("docs")
    val movieList: List<MovieDTOKin>,
    @SerializedName("total")
    val total: Int,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("page")
    val page: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("message")
    val errorMessage: String?
)

data class MovieDTOKin(
    @SerializedName("externalId")
    val externalId: ExternalId,
    @SerializedName("logo")
    val logo: Logo,
    @SerializedName("poster")
    val poster: Poster,
    @SerializedName("rating")
    val rating: Rating,
    @SerializedName("votes")
    val votes: Votes,
    @SerializedName("movieLength")
    val movieLength: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("type")
    val type: String,
    @SerializedName("name")
    val name: String?,
    @SerializedName("description")
    val description: String,
    @SerializedName("year")
    val year: Int,
    @SerializedName("alternativeName")
    val alternativeName: String,
    @SerializedName("names")
    val names: List<Names>
)

data class Names(
    @SerializedName("_id")
    val id: String,
    @SerializedName("name")
    val name: String
)

data class Votes(
    @SerializedName("_id")
    val id: String,
    @SerializedName("kp")
    val kp: Int,
    @SerializedName("imdb")
    val imdb: Int,
    @SerializedName("filmCritics")
    val filmCritics: Int,
    @SerializedName("russianFilmCritics")
    val russianFilmCritics: Int,
    @SerializedName("await")
    val await: Int
)

data class Rating(
    @SerializedName("_id")
    val id: String,
    @SerializedName("kp")
    val kp: Double,
    @SerializedName("imdb")
    val imdb: Double,
    @SerializedName("filmCritics")
    val filmCritics: Double,
    @SerializedName("russianFilmCritics")
    val russianFilmCritics: Int,
    @SerializedName("await")
    val await: Double
)

data class Poster(
    @SerializedName("_id")
    val id: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("previewUrl")
    val previewUrl: String
)

data class Logo(
    @SerializedName("_id")
    val id: String
)

data class ExternalId(
    @SerializedName("_id")
    val id: String,
    @SerializedName("imdb")
    val imdb: String
)