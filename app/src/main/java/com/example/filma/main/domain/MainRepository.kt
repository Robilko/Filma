package com.example.filma.main.domain

import com.example.filma._core.data.api.model.ResponseMovieListDTO

interface MainRepository {
    suspend fun getMostPopularMoviesList(): Result<ResponseMovieListDTO>
}