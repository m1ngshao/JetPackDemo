package com.example.jetpackdemo.ui.main.ui.dashboard

import com.example.jetpackdemo.ui.main.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitBuilder {
    private const val BASE_URL = "https://api.bilibili.com/"

    private fun getRetrofit() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService : ApiService = getRetrofit().create(ApiService::class.java)
}