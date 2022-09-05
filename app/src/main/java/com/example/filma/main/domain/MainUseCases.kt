package com.example.filma.main.domain

import com.example.filma._core.ui.MapperMovieDTOKinToUi
import com.example.filma._core.ui.model.MovieKin

class MainUseCases(private val repository: MainRepository) {

    suspend fun getNewReleasesMovieList(): Result<List<MovieKin>> {
        val result = repository.getNewReleasesMovieList()
        return result.map { responseDTO ->
            MapperMovieDTOKinToUi(responseDTO.movieList)
        }
    }
}