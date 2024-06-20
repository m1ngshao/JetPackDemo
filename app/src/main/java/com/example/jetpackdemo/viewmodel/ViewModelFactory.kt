package com.example.jetpackdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackdemo.api.ApiHelper
import com.example.jetpackdemo.api.Repository
import java.lang.IllegalArgumentException

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DashboardViewModel::class.java)){
            return DashboardViewModel(Repository(apiHelper)) as T
        }else if (modelClass.isAssignableFrom(ListItemDetailViewModel::class.java)){
            return ListItemDetailViewModel(Repository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}