package com.example.movietest.api

import com.example.movietest.BuildConfig
import com.example.movietest.data.ResponseTopMovies
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieService {

    @GET("/svc/movies/v2//reviews/all.json?api-key=${BuildConfig.API_KEY}")
   suspend fun getMoves(
        @Query("offset") offset:Int
   ): ResponseTopMovies

    companion object {
        private const val BASE_URL = "https://api.nytimes.com/"

        fun get(): MovieService = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }).build()
            )
            .build().create(MovieService::class.java)
    }
}

