package com.example.filma.search.domain

import com.example.filma._core.ui.MapperMovieDtoToUi
import com.example.filma._core.ui.model.Movie

class SearchUseCases(private val repository: SearchRepository) {
    suspend fun getMovieList(name: String, pageNumber: Int): Result<List<Movie>> {
        val result = repository.getMovieListByName(name = name, pageNumber = pageNumber)
        return result.map { responseDTO ->
            MapperMovieDtoToUi(responseDTO.movieList!!)
        }
    }
}