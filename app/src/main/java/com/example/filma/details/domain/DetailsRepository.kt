package com.example.filma.details.domain

import com.example.filma._core.data.api.model.DetailsMovieDTO

interface DetailsRepository {
    suspend fun getMovieDetails(movieId: String): Result<DetailsMovieDTO>
}