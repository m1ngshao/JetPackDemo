package com.example.jetpackdemo.ui.main

import com.example.jetpackdemo.PreciousBean
import retrofit2.http.GET

interface ApiService {

    @GET("x/web-interface/popular/precious")
    suspend fun getPrecious() : PreciousBean
}