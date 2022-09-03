package com.example.filma.main.domain

import com.example.filma._core.ui.MovieDtoToUiMapper
import com.example.filma._core.ui.model.Movie

class MainUseCases(private val repository: MainRepository) {

    suspend fun getMostPopularMoviesList(): Result<List<Movie>> {
        val result = repository.getMostPopularMoviesList()
        return result.map { responseDTO ->
            MovieDtoToUiMapper(responseDTO.movies)
        }
    }
}