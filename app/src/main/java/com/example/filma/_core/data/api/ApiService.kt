package com.example.filma._core.data.api

import com.example.filma.*
import com.example.filma._core.data.api.model.DetailsMovieDTO
import com.example.filma._core.data.api.model.ResponseDTO
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(TITLE)
    suspend fun getMovieDetails(
        @Query("search") id: String,
        @Query("token") apiKey: String = API_KEY
    ): DetailsMovieDTO

    @GET(NEW_RELEASES_LIST)
    suspend fun getNewReleasesMovieList(
        @Query("token") apiKey: String = API_KEY,
        @Query("page") pageNumber: Int = 1,
        @Query("limit") limit: Int = MOVIES_ON_PAGE_LIMIT
    ) : ResponseDTO

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