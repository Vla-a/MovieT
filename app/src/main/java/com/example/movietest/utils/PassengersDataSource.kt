package com.example.movietest.utils

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movietest.api.MovieService
import com.example.movietest.data.Results

class PassengersDataSource(private val api: MovieService) : PagingSource<Int, Results>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Results> {
        return try {
            val nextPageNumber = params.key ?: 0
            val response = api.getMoves(nextPageNumber)

            LoadResult.Page(
                data = response.results,
                prevKey = if (nextPageNumber > 0) nextPageNumber - 1 else null,
                nextKey = if (nextPageNumber < 10) nextPageNumber + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Results>): Int? {
        TODO("Not yet implemented")
    }
}



