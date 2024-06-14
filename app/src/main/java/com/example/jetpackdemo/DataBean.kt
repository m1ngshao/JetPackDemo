package com.example.jetpackdemo

import com.google.gson.annotations.SerializedName

data class DataBean (
    @SerializedName("title")
    var title : String,
    @SerializedName("list")
    var list : List<ContentBean>,
)