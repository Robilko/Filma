package com.example.filma.main.data

import com.example.filma._core.data.api.ApiService
import com.example.filma._core.data.api.model.ResponseDTO
import com.example.filma.main.domain.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class MainRepositoryImpl(
    private val apiService: ApiService
) : MainRepository {
    override suspend fun getNewReleasesMovieList(pageNumber: Int): Result<ResponseDTO> {
        return try {
            val response = withContext(Dispatchers.IO) { apiService.getNewReleasesMovieList(pageNumber = pageNumber) }

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