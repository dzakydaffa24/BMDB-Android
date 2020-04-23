package com.example.bmdb.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory()).build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl("http://10.0.2.2:8000/api/")
    .build()

interface APIService {
    @GET("movies")
    suspend fun showList(): List<Movie>
}

object MovieAPI{
    val retrofitService = retrofit.create(APIService::class.java)
}