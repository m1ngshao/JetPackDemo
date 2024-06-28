package com.example.jetpackdemo.kotlinLearn

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


fun main() {

    println("currentThread : ${Thread.currentThread()}")
    val scope = CoroutineScope(Dispatchers.Default)
    scope.launch {
        println("currentThread : ${Thread.currentThread()}")
    }
    Thread.sleep(1000)
}