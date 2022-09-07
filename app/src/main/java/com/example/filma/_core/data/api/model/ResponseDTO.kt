package com.example.filma._core.data.api.model

import com.google.gson.annotations.SerializedName

data class ResponseDTO(
    @SerializedName("docs")
    val movieList: List<MovieFromListDTO>,
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

data class MovieFromListDTO(
    @SerializedName("externalId")
    val externalId: ExternalIdDTO,
    @SerializedName("logo")
    val logo: LogoDTO,
    @SerializedName("poster")
    val poster: PosterDTO,
    @SerializedName("rating")
    val rating: RatingDTO,
    @SerializedName("votes")
    val votes: VoteDTO,
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
    val alternativeName: String?,
    @SerializedName("names")
    val names: List<NameDTO>
)

data class NameDTO(
    @SerializedName("_id")
    val id: String,
    @SerializedName("name")
    val name: String
)

data class VoteDTO(
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

data class RatingDTO(
    @SerializedName("_id")
    val id: String,
    @SerializedName("kp")
    val kp: Double,
    @SerializedName("imdb")
    val imdb: Double,
    @SerializedName("filmCritics")
    val filmCritics: Double,
    @SerializedName("russianFilmCritics")
    val russianFilmCritics: Double,
    @SerializedName("await")
    val await: Double
)

data class PosterDTO(
    @SerializedName("_id")
    val id: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("previewUrl")
    val previewUrl: String
)

data class LogoDTO(
    @SerializedName("_id")
    val id: String
)

data class ExternalIdDTO(
    @SerializedName("_id")
    val id: String,
    @SerializedName("imdb")
    val imdb: String
)