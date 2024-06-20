package com.example.jetpackdemo.bean

import com.google.gson.annotations.SerializedName

data class DataBean (
    @SerializedName("title")
    var title : String,
    @SerializedName("list")
    var list : List<ContentBean>,
)