package com.example.filma._core.data.api

import com.example.filma.API_KEY
import com.example.filma.BASE_URL
import com.example.filma.MOST_POPULAR_MOVIES
import com.example.filma.TITLE
import com.example.filma._core.data.api.model.MovieDetailsDTO
import com.example.filma._core.data.api.model.ResponseMovieListDTO
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(MOST_POPULAR_MOVIES)
    suspend fun getMostPopularMoviesList(
        @Query("lang") language: String = "en",
        @Query("apiKey") apiKey: String = API_KEY
    ): ResponseMovieListDTO

    @GET(TITLE)
    suspend fun getMovieDetails(
        @Query("lang") language: String = "en",
        @Query("apiKey") apiKey: String = API_KEY,
        @Query("id") id: String
    ): MovieDetailsDTO

    companion object {
        fun getInstance(): ApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpClient.Builder().build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(ApiService::class.java)
        }
    }
}