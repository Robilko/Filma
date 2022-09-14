package com.example.filma.main.data

import android.util.Log
import com.example.filma.TAG
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
            Log.d(TAG, "MainRepositoryImpl getNewReleasesMovieList() called with: pageNumber = $pageNumber \n")
            val response =
                withContext(Dispatchers.IO) { apiService.getNewReleasesMovieList(pageNumber = pageNumber) }

            if (response.movieList != null) {
                Log.d(TAG, "result of request: \n ${response.movieList} \n \n")
                Result.success(value = response)
            } else {
                Log.d(TAG, "result of request: \n ${response.errorMessage} \n")
                Result.failure(IOException("Нет элементов"))
            }
        } catch (ex: HttpException) {
            Log.d(TAG, "MainRepositoryImpl HTTP EXCEPTION - ${ex.code()} \n ${ex.message()}")
            Result.failure(exception = ex)
        } catch (ex: IOException) {
            Log.d(TAG, "MainRepositoryImpl EXCEPTION - ${ex.message}")
            Result.failure(exception = ex)
        }
    }
}