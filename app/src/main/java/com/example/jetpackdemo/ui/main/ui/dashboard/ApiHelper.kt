package com.example.jetpackdemo.ui.main.ui.dashboard

import com.example.jetpackdemo.ui.main.ApiService

class ApiHelper(private val apiService: ApiService) {
    suspend fun getPrecious() = apiService.getPrecious()
}