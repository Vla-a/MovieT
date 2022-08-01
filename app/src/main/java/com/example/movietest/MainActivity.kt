package com.example.movietest


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movietest.adapter.PassengerListAdapter
import com.example.movietest.adapter.PassengersLoadStateAdapter
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


        val passengerListAdapter = PassengerListAdapter()

        binding.rvMovies.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.rvMovies.adapter = passengerListAdapter


        val passengersAdapter = PassengerListAdapter()
        binding.rvMovies.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = passengersAdapter
            setHasFixedSize(true)
        }


        binding.rvMovies.adapter = passengersAdapter.withLoadStateHeaderAndFooter(
            header = PassengersLoadStateAdapter { passengersAdapter.retry() },
            footer = PassengersLoadStateAdapter { passengersAdapter.retry() }
        )

        lifecycleScope.launch {
            moveViewModel.passengers.collectLatest { pagedData ->
                passengersAdapter.submitData(pagedData)
            }
        }
    }
}

