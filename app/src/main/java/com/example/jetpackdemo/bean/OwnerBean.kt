package com.example.jetpackdemo.bean

import com.google.gson.annotations.SerializedName

data class Owner(
    @SerializedName("name")
    var name : String
)