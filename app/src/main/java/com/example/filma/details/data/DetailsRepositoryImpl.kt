package com.example.filma.details.data

import com.example.filma._core.data.api.ApiService
import com.example.filma._core.data.api.model.ResponseMovieDetailsDTO
import com.example.filma.details.domain.DetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class DetailsRepositoryImpl(private val apiService: ApiService) : DetailsRepository {
    override suspend fun getMovieDetails(movieId: String): Result<ResponseMovieDetailsDTO> {
        return try {
            val response = withContext(Dispatchers.IO) {
                apiService.getMovieDetails(id = movieId)
            }

            if (response.errorMessage.isNullOrEmpty()) {
                Result.success(value = response)
            } else {
                Result.failure(exception = Throwable(message = "${response.errorMessage}"))
            }
        } catch (ex: HttpException) {
            Result.failure(exception = ex)
        } catch (ex: IOException) {
            Result.failure(exception = ex)
        }
    }
}