package com.example.filma.details.data

import android.util.Log
import com.example.filma.TAG
import com.example.filma._core.data.api.ApiService
import com.example.filma._core.data.api.model.DetailsMovieDTO
import com.example.filma.details.domain.DetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class DetailsRepositoryImpl(private val apiService: ApiService) : DetailsRepository {
    override suspend fun getMovieDetails(movieId: String): Result<DetailsMovieDTO> {
        return try {
            val response = withContext(Dispatchers.IO) {
                apiService.getMovieDetails(id = movieId)
            }
            Log.d(TAG, "getMovieDetails() called with: movieId = $movieId, response = \n $response")

            Result.success(value = response)

        } catch (ex: HttpException) {
            Result.failure(exception = ex)
        } catch (ex: IOException) {
            Result.failure(exception = ex)
        }
    }
}