package com.example.filma._core.ui.model

data class MovieDetails(
    val id: String,
    val title: String,
    val originalTitle: String?,
    val fullTitle: String,
    val type: String,
    val year: String,
    val imageUrl: String?,
    val releaseDate: String,
    val runtimeMins: String,
    val runtimeStr: String,
    val description: String,
    val descriptionLocal: String?,
    val descriptionLocalIsRtl: Boolean,
    val directors: String,
    val actorList: List<Actor>,
    val genres: String,
    val imDbRating: String,
    val keywords: String,
    val keywordList: List<String>,
    val similars: List<Movie>
)