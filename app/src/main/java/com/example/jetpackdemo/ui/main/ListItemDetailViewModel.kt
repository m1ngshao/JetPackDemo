package com.example.jetpackdemo.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.jetpackdemo.ui.main.ui.dashboard.Repository
import com.example.jetpackdemo.ui.main.ui.dashboard.Resource
import kotlinx.coroutines.Dispatchers

class ListItemDetailViewModel(private val repository: Repository) : ViewModel() {
    fun getDetailContent() = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getDetailContent()))
        }catch(exception : Exception){
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}