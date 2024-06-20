package com.example.jetpackdemo.api

class ApiService {
    val api : Api = RetrofitBuilder.api
    suspend fun getPrecious() = api.getPrecious()
    suspend fun getDetailContent(aid : Long) = api.getDetailContent(aid)
}