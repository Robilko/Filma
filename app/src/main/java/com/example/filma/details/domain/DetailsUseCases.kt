package com.example.filma.details.domain

import android.util.Log
import com.example.filma._core.ui.MapperDetailsMovieDtoToUi
import com.example.filma._core.ui.model.DetailsMovie

class DetailsUseCases(private val repository: DetailsRepository) {

    suspend fun getMovieDetails(movieId: String) : Result<DetailsMovie> {
        val result = repository.getMovieDetails(movieId)
        return result.map { responseDTO ->
            Log.d("TAG", "getMovieDetails() called with: responseDTO = $responseDTO")
            MapperDetailsMovieDtoToUi(responseDTO)
        }
    }
}