package com.example.jetpackdemo.ui.main

import com.example.jetpackdemo.Bean.PreciousBean
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("x/web-interface/popular/precious")
    suspend fun getPrecious() : PreciousBean
}