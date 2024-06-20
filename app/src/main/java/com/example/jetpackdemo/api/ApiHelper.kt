package com.example.jetpackdemo.api

class ApiHelper(private val apiService: ApiService, private val aid :Long?) {
    suspend fun getPrecious() = apiService.getPrecious()
    suspend fun getDetailContent() = apiService.getDetailContent(aid)
}