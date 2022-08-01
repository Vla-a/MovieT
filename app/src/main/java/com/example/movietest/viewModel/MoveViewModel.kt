package com.example.movietest.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.movietest.api.MovieService
import com.example.movietest.utils.PassengersDataSource

class MoveViewModel(
//    private val moveRepository: MoveRepository
    api: MovieService
) : ViewModel() {

    val passengers =
        Pager(config = PagingConfig(pageSize = 1), pagingSourceFactory = {
            PassengersDataSource(api)
        }).flow.cachedIn(viewModelScope)


}