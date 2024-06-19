package com.example.jetpackdemo.ui.main.ui.dashboard

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class Repository(private val apiHelper: ApiHelper) {
    suspend fun getPrecious() = apiHelper.getPrecious()

    suspend fun getDetailContent() = apiHelper.getDetailContent()
}