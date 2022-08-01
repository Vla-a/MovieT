package com.example.movietest

import android.app.Application
import com.example.movietest.api.MovieService
import com.example.movietest.repository.MoveRepository
import com.example.movietest.viewModel.MoveViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import org.koin.dsl.module


class MySuperApp : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()


        startKoin {
            androidContext(this@MySuperApp)
            modules(listOf(viewModels, repository, currencyApi))
        }
    }

    private val viewModels = module {
        viewModel { MoveViewModel(get()) }

    }

    private val repository = module {

factory { MoveRepository(get()) }
    }

    private val currencyApi = module {
        single { MovieService.get() }
    }


}