package com.example.jetpackdemo.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ContentBean <T>  (
    @SerializedName("aid")
    var aid :Long,
    @SerializedName("pic")
    var pic : String,
    @SerializedName("title")
    var title : T,
    @SerializedName("desc")
    var des : String,
    @SerializedName("owner")
    var owner : Owner
)

data class Owner(
    @SerializedName("name")
    var name : String
)