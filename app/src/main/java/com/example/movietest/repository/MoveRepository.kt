package com.example.movietest.repository

import com.example.movietest.api.MovieService
import com.example.movietest.data.Movie
import com.example.movietest.data.ResponseTopMovies
import kotlinx.coroutines.flow.Flow

class MoveRepository (
    private val moveApi: MovieService
        )
{
//   suspend fun getMove(): List<Movie>  {
//       val myMovie = moveApi.getMoves().body()!!.results.map {
//           Movie(
//               name = it.displayTitle.toString(),
//               image = it.multimedia?.src.toString(),
//               about = it.summaryShort.toString()
//           )
//       }
//return myMovie
//    }
}
