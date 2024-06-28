package com.example.jetpackdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.jetpackdemo.api.ApiService
import com.example.jetpackdemo.api.Resource
import kotlinx.coroutines.Dispatchers

class ListItemDetailViewModel: ViewModel() {


//    fun getDetailContent() = liveData(Dispatchers.IO){
//        emit(Resource.loading(data = null))
//        try {
//            emit(Resource.success(data = apiService.getDetailContent(aid)))
//        }catch(exception : Exception){
//            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
//        }
//    }

//    fun getDetailContent()

}