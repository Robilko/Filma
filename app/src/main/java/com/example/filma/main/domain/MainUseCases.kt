package com.example.filma.main.domain

import com.example.filma._core.ui.MapperMovieDtoToUi
import com.example.filma._core.ui.model.Movie

class MainUseCases(private val repository: MainRepository) {

    suspend fun getMovieList(pageNumber: Int): Result<List<Movie>> {
        val result = repository.getNewReleasesMovieList(pageNumber)
        return result.map { responseDTO ->
            MapperMovieDtoToUi(responseDTO.movieList!!)
        }
    }
}