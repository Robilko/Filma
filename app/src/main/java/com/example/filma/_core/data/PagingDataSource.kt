package com.example.filma._core.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.filma._core.ui.model.Movie

abstract class PagingDataSource : PagingSource<Int, Movie>(){

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}