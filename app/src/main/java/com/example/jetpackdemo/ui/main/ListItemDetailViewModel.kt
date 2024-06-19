package com.example.jetpackdemo.ui.main

import androidx.lifecycle.ViewModel

class ListItemDetailViewModel : ViewModel() {
    private var detailMessage  = ""
    fun setDetailMessage(str : String){
        this.detailMessage = str
    }

    fun getDetailMessage() : String{
        return detailMessage
    }
}