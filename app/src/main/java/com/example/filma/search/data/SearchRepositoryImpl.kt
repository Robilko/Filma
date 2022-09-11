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
            val response = withContext(Dispatchers.IO) {
                apiService.getMovieListSearchedByName(
                    name = name,
                    pageNumber = pageNumber
                )
            }

            Result.success(value = response)
        } catch (ex: HttpException) {
            Result.failure(exception = ex)
        } catch (ex: IOException) {
            Log.d(TAG, "${ex.message}")
            Result.failure(exception = ex)
        }
    }
}
