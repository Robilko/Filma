package com.example.filma._core.data.api.model

import com.google.gson.annotations.SerializedName

data class DetailsMovieDTO(

    @SerializedName("id")
    val id: Int,
    @SerializedName("externalId")
    val externalId: DetailsMovieExternalIdDTO,
    @SerializedName("type")
    val type: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("slogan")
    val slogan: String?,
    @SerializedName("year")
    val year: Int,
    @SerializedName("poster")
    val poster: DetailsMoviePosterDTO,
    @SerializedName("rating")
    val rating: DetailsMovieRatingDTO,
    @SerializedName("videos")
    val videos: VideoDTO,
    @SerializedName("movieLength")
    val movieLength: Int,
    @SerializedName("facts")
    val facts: List<FactDTO>,
    @SerializedName("genres")
    val genres: List<GenreDTO>,
    @SerializedName("countries")
    val countries: List<CountryDTO>,
    @SerializedName("persons")
    val persons: List<PersonDTO>
)

data class PersonDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("enName")
    val enName: String,
    @SerializedName("photo")
    val photoUrl: String,
    @SerializedName("enProfession")
    val enProfession: String,
    @SerializedName("description")
    val description: String
)

data class CountryDTO(
    @SerializedName("name")
    val name: String
)

data class GenreDTO(
    @SerializedName("name")
    val name: String
)

data class FactDTO(
    @SerializedName("value")
    val value: String
)

data class VideoDTO(
    @SerializedName("trailers")
    val trailers: List<TrailerDTO>,
    @SerializedName("teasers")
    val teasers: List<String>
)

data class TrailerDTO(
    @SerializedName("_id")
    val Id: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("site")
    val site: String,
    @SerializedName("size")
    val size: Int,
    @SerializedName("type")
    val type: String
)

data class DetailsMovieRatingDTO(
    @SerializedName("tmdb")
    val tmdb: Double,
    @SerializedName("kp")
    val kp: Double,
    @SerializedName("imdb")
    val imdb: Double
)

data class DetailsMoviePosterDTO(
    @SerializedName("url")
    val url: String,
    @SerializedName("previewUrl")
    val previewUrl: String
)

data class DetailsMovieExternalIdDTO(
    @SerializedName("tmdb")
    val tmdb: Int?,
    @SerializedName("imdb")
    val imdb: String?
)
