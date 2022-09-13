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
            Log.d(TAG, "DetailsRepositoryImpl getMovieDetails() called with: movieId = $movieId \n")
            val response = withContext(Dispatchers.IO) {
                apiService.getMovieDetails(id = movieId)
            }
            Log.d(TAG, "result of request: \n $response \n \n")
            Result.success(value = response)

        } catch (ex: HttpException) {
            Log.d(TAG, "DetailsRepositoryImpl HTTP EXCEPTION - ${ex.response()?.code()})")
            if (ex.response()?.code() == 403)
                Result.failure(Throwable(message = "Вы сделали более 200 запросов за сутки"))
            else
                Result.failure(exception = ex)
        } catch (ex: IOException) {
            Log.d(TAG, "DetailsRepositoryImpl EXCEPTION- ${ex.message}")
            Result.failure(exception = ex)
        }
    }
}