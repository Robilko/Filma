package com.example.filma.main.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.filma.MOVIES_ON_PAGE_LIMIT
import com.example.filma._core.ui.model.Movie
import com.example.filma.main.data.MoviePagingDataSource
import com.example.filma.main.domain.MainUseCases
import kotlinx.coroutines.flow.Flow

class MainViewModel(private val useCases: MainUseCases) : ViewModel() {

    val items: Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(pageSize = MOVIES_ON_PAGE_LIMIT)
    ) { MoviePagingDataSource(useCases = useCases) }.flow.cachedIn(viewModelScope)
}