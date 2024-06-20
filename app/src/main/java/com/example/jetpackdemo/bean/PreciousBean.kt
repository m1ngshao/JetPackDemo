package com.example.jetpackdemo.bean

import com.google.gson.annotations.SerializedName

data class PreciousBean(
    @SerializedName("code")
    var code : Int,
    @SerializedName("message")
    var message : String,
    @SerializedName("ttl")
    var ttl : Int,
    @SerializedName("data")
    var data : DataBean
)