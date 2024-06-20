package com.example.jetpackdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.jetpackdemo.api.ApiService
import com.example.jetpackdemo.api.Resource
import kotlinx.coroutines.Dispatchers

class DashboardViewModel : ViewModel() {

    private val apiService by lazy {
        ApiService()
    }
    fun getPrecious() = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiService.getPrecious()))
        }catch(exception : Exception){
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}