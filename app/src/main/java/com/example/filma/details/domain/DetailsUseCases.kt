package com.example.filma.details.domain

import com.example.filma._core.ui.MovieDetailsDtoToUiMapper
import com.example.filma._core.ui.model.MovieDetails

class DetailsUseCases(private val repository: DetailsRepository) {

    suspend fun getMovieDetails(movieId: String) : Result<MovieDetails> {
        val result = repository.getMovieDetails(movieId)
        return result.map { responseDTO ->
            MovieDetailsDtoToUiMapper(responseDTO)
        }
    }
}