package com.example.jetpackdemo.ui.main

import com.example.jetpackdemo.Bean.DetailBean
import com.example.jetpackdemo.Bean.PreciousBean
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("x/web-interface/popular/precious")
    suspend fun getPrecious() : PreciousBean

    @GET("/x/web-interface/view")
    suspend fun getDetailContent(@Query("aid") aid : Long?) : DetailBean
}