package com.example.filma.main.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.filma.STARTING_PAGE_KEY
import com.example.filma._core.ui.model.Movie
import com.example.filma.main.domain.MainUseCases

class MoviePagingDataSource(private val useCases: MainUseCases) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val currentLoadingPageKey = params.key ?: 1
            val resultData = useCases.getNewReleasesMovieList(pageNumber = currentLoadingPageKey)
            val movieList = resultData.getOrThrow()
            val prevKey =
                if (currentLoadingPageKey == STARTING_PAGE_KEY) null else currentLoadingPageKey - 1
            val nextKey = currentLoadingPageKey + 1
            LoadResult.Page(
                data = movieList,
                prevKey = prevKey,
                nextKey = nextKey
            )

        } catch (ex: Exception) {
            return LoadResult.Error(ex)
        }
    }
}