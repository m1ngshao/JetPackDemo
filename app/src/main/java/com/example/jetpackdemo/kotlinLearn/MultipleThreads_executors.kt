package com.example.jetpackdemo.kotlinLearn

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

fun main(){

    val threadPool = Executors.newFixedThreadPool(8)

    for (i in 1..8){
        threadPool.submit {
            println("任务执行: $i, 线程: ${Thread.currentThread().name}")
        }
    }

    threadPool.shutdown()
    threadPool.awaitTermination(1, TimeUnit.DAYS)

    println("方法结束")
}

