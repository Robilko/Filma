package com.example.filma.search.domain

import com.example.filma._core.data.api.model.ResponseDTO

interface SearchRepository {
    suspend fun getMovieListByName(name: String, pageNumber: Int): Result<ResponseDTO>
}