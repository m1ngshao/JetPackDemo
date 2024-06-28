package com.example.jetpackdemo.api

import com.example.jetpackdemo.bean.DetailBean
import io.reactivex.rxjava3.core.Observable

class ApiService {
    val api : Api = RetrofitBuilder.api
    val apirx : Api = RetrofitBuilder.apirx
    suspend fun getPrecious() = api.getPrecious()
    fun getDetailContent(aid : Long) : Observable<DetailBean>{
        return apirx.getDetailContent(aid)
    }
}