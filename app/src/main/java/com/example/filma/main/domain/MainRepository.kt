package com.example.filma.main.domain

import com.example.filma._core.data.api.model.ResponseDTO

interface MainRepository {
    suspend fun getNewReleasesMovieList(pageNumber: Int): Result<ResponseDTO>
}