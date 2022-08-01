package com.example.movietest



import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movietest.adapter.MovieAdapter
import com.example.movietest.adapter.PassengerListAdapter
import com.example.movietest.databinding.ActivityMainBinding
import com.example.movietest.viewModel.MoveViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {


     val moveViewModel: MoveViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val movieAdapter = MovieAdapter()
//
//        binding.rvMovies.layoutManager =
//            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
//        binding.rvMovies.adapter = movieAdapter

        val passengerListAdapter = PassengerListAdapter()

        binding.rvMovies.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.rvMovies.adapter = passengerListAdapter

//        moveViewModel.moveLiveData.observe(this) {
//            passengerListAdapter. (PagingData.from(it))
//        }
        val passengersAdapter = PassengerListAdapter()
        binding.rvMovies.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = passengersAdapter
            setHasFixedSize(true)
        }

        lifecycleScope.launch {
            moveViewModel.passengers.collectLatest { pagedData ->

                Log.e("RER", pagedData?.toString())
                passengersAdapter.submitData(pagedData)
            }
        }
    }
    }

