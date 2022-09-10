package com.example.filma.search.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.filma.TAG
import com.example.filma._core.ui.model.Movie
import com.example.filma.search.data.SearchMovieRepo
import kotlinx.coroutines.flow.Flow

class SearchViewModel(private val repository: SearchMovieRepo) : ViewModel() {

    private var currentSearchName: String? = null

    private var currentSearchResult: Flow<PagingData<Movie>>? = null

    fun searchMovies(name: String): Flow<PagingData<Movie>> {
        Log.d(TAG, "searchMovies() called with: name = $name")
        val lastResult = currentSearchResult
        if (name == currentSearchName && lastResult != null) {
            return lastResult
        }
        currentSearchName = name
        val newResult = repository.searchMovies(name).cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }
}