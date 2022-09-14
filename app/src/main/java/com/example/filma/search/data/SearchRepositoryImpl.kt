package com.example.filma.search.data

import android.util.Log
import com.example.filma.TAG
import com.example.filma._core.data.api.ApiService
import com.example.filma._core.data.api.model.ResponseDTO
import com.example.filma.search.domain.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class SearchRepositoryImpl(private val apiService: ApiService) : SearchRepository {
    override suspend fun getMovieListByName(name: String, pageNumber: Int): Result<ResponseDTO> {
        return try {
            Log.d(TAG, "SearchRepositoryImpl getMovieListByName() called with: name = $name, pageNumber = $pageNumber \n")
            val response = withContext(Dispatchers.IO) {
                apiService.getMovieListSearchedByName(
                    name = name,
                    pageNumber = pageNumber
                )
            }

            Log.d(TAG, "result of request: \n ${response.movieList} \n ${response.errorMessage}\n ${response.total} \n\n")
            if (response.movieList != null) {
                Result.success(value = response)
            } else {
                Result.failure(IOException("Нет элементов"))
            }

        } catch (ex: HttpException) {
            Log.d(TAG, "SearchRepositoryImpl HTTP EXCEPTION - ${ex.code()} \n ${ex.message()}")
            Result.failure(exception = ex)
        } catch (ex: IOException) {
            Log.d(TAG, "SearchRepositoryImpl EXCEPTION - ${ex.message}")
            Result.failure(exception = ex)
        }
    }
}
