package com.example.movietest.viewModel

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.movietest.api.MovieService
import com.example.movietest.data.Movie
import com.example.movietest.data.ResponseTopMovies
import com.example.movietest.repository.MoveRepository
import com.example.movietest.utils.PassengersDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoveViewModel(
//    private val moveRepository: MoveRepository
api: MovieService
) : ViewModel() {

    val moveLiveData: MutableLiveData<List<Movie>> = MutableLiveData()

    val passengers =
        Pager(config = PagingConfig(pageSize = 10), pagingSourceFactory = {
            PassengersDataSource(api)
        }).flow.cachedIn(viewModelScope)



init {
//    getList()
}
//    private fun getList() {
//        viewModelScope.launch() {
//            moveLiveData.postValue(moveRepository.getMove())
//        }
//    }
}