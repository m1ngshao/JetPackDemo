package com.example.jetpackdemo.Bean

import com.google.gson.annotations.SerializedName

data class Owner(
    @SerializedName("name")
    var name : String
)