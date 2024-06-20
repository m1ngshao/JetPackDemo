package com.example.jetpackdemo.api

class Repository(private val apiHelper: ApiHelper) {
    suspend fun getPrecious() = apiHelper.getPrecious()

    suspend fun getDetailContent() = apiHelper.getDetailContent()
}