package com.example.filma.search.data


import com.example.filma.STARTING_PAGE_KEY
import com.example.filma._core.data.PagingDataSource
import com.example.filma._core.ui.model.Movie
import com.example.filma.search.domain.SearchUseCases

class SearchPagingDataSource(private val useCases: SearchUseCases, private val name: String) : PagingDataSource(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val currentLoadingPageKey = params.key ?: 1
            val resultData = useCases.getMovieList(name = name, pageNumber = currentLoadingPageKey)
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