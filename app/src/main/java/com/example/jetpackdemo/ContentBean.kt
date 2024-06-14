package com.example.jetpackdemo

import com.google.gson.annotations.SerializedName

data class ContentBean(
    @SerializedName("pic")
    var pic : String,
    @SerializedName("title")
    var title : String,
    @SerializedName("desc")
    var des : String
)