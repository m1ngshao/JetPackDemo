package com.example.jetpackdemo.ui.main

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private val usd_to_eu_rate = 0.93f
    private var dollartext = ""
    private var result = 0f

    fun setAmount(value : String){
        this.dollartext = value
        result = dollartext.toFloat() * usd_to_eu_rate
    }

    fun getResult() : Float{
        return result
    }
}