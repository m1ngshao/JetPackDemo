package com.example.jetpackdemo.ui.main.ui.dashboard

import com.example.jetpackdemo.ui.main.ApiService

class ApiHelper(private val apiService: ApiService,private val aid :Long?) {
    suspend fun getPrecious() = apiService.getPrecious()
    suspend fun getDetailContent() = apiService.getDetailContent(aid)
}