package com.example.jetpackdemo.kotlinLearn

fun main(){
    val increase = SharedResource()

    val thread1 = Thread{
        for (i in 1..100){
            increase.increase()
        }
    }

    val thread2 = Thread{
        for (i in 1..100){
            increase.increase()
        }
    }

    thread1.start()
    thread2.start()

    thread1.join()
    thread2.join()

    println("count: ${increase.getCount()}")
}

class SharedResource{
    private var count:Int = 0
    private val lock = Any()

    fun increase(){
        synchronized(lock){
            count++
        }
    }

    fun getCount(): Int{
        return count
    }
}