package com.example.filma.search.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.filma.MOVIES_ON_PAGE_LIMIT
import com.example.filma.search.domain.SearchUseCases

class SearchMovieRepo(private val useCases: SearchUseCases) {
    fun searchMovies(name: String) = Pager(
        pagingSourceFactory = {SearchPagingDataSource(useCases, name)},
        config = PagingConfig(pageSize = MOVIES_ON_PAGE_LIMIT)
    ).flow
}