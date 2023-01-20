package com.example.footballmatchtrackertest.data.api

import com.example.cfttestapp.data.api.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.soccersapi.com/v2.2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}