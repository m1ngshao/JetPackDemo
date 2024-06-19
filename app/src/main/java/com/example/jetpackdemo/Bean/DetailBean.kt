package com.example.jetpackdemo.Bean

import com.google.gson.annotations.SerializedName

class DetailBean(
    @SerializedName("code")
    var code : Int,
    @SerializedName("message")
    var message : String,
    @SerializedName("ttl")
    var ttl : Int,
    @SerializedName("data")
    var data : DetailContentBean
)