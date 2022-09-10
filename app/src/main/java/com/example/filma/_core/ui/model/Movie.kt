package com.example.filma._core.ui.model

data class Movie(
    val id: String,
    val title: String,
    val alternativeTitle: String,
    val imageUrl: String?,
    val imDbRating: String,
    val kinopoiskRating: String?,
    val year: String
)