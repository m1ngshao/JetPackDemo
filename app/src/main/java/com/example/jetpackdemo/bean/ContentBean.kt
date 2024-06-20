package com.example.jetpackdemo.bean

import com.google.gson.annotations.SerializedName

data class ContentBean(
    @SerializedName("aid")
    var aid :Long,
    @SerializedName("pic")
    var pic : String,
    @SerializedName("title")
    var title : String,
    @SerializedName("desc")
    var des : String,
    @SerializedName("owner")
    var owner : Owner
)