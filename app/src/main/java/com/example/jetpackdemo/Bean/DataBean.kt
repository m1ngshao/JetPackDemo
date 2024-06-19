package com.example.jetpackdemo.Bean

import com.example.jetpackdemo.Bean.ContentBean
import com.google.gson.annotations.SerializedName

data class DataBean (
    @SerializedName("title")
    var title : String,
    @SerializedName("list")
    var list : List<ContentBean>,
)