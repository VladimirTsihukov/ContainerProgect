package com.androidapp.containerprogect.coroutineRetrofit

import com.androidapp.containerprogect.coroutineRetrofit.data.MoviesList
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("popular")
    suspend fun getListMovie() : Response<MoviesList>
}