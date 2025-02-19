package com.example.jetpackdemo.kotlinLearn

import okio.withLock
import java.util.concurrent.locks.ReentrantLock

fun main(){
    val resource = SharedResource1()

    val thread1 = Thread {
        for(i in 1..100){
            resource.increase()
        }
    }

    val thread2 = Thread {
        for(i in 1..100){
            resource.increase()
        }
    }

    thread1.start()
    thread2.start()

    thread1.join()
    thread2.join()

    println("count: ${resource.getCount()}")
}

class SharedResource1{
    private var count:Int = 0
    private val lock = ReentrantLock()

    fun increase(){
        lock.withLock {
            count++
        }
    }

    fun getCount(): Int{
        return count
    }
}