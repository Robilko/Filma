package com.example.filma._core.ui.model

data class DetailsMovie(
    val id: Int,
    val imdbId: String,
    val type: String,
    val name: String,
    val description: String,
    val slogan: String,
    val year: String,
    val posterUrl: String?,
    val kinopoiskRating: String,
    val imDbRating: String,
    val videoTrailerUrl: String?,
    val movieLength: String,
    val facts: List<String>,
    val genres: String,
    val countries: String,
    val directors: List<Person>,
    val actors: List<Person>
)

data class Person(
    val id: Int,
    val name: String,
    val enName: String,
    val photoUrl: String,
    val enProfession: String,
    val description: String?
)