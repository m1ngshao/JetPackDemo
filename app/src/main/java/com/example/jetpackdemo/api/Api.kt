package com.example.jetpackdemo.api

import com.example.jetpackdemo.bean.DetailBean
import com.example.jetpackdemo.bean.PreciousBean
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("x/web-interface/popular/precious")
    suspend fun getPrecious() : PreciousBean

    @GET("/x/web-interface/view")
    fun getDetailContent(
        @Query("aid") aid : Long?
    ) : Observable<DetailBean>

}