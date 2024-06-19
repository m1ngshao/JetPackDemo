package com.example.jetpackdemo.kotlinLearn

fun main() {
    val list = listOf<Int>(1, 2, 3, 45, 6)
    list.apply {
        first() + first()
    }
    val str = "我是字符串"
    val result =  str.run(::getlet)
        .run{
            this == "我是空值"
        }
    println(result)
}

fun getlet(str: String?): String {
    return str?.let {
        "我是有值的"
    } ?: "我是空值"
}

fun getlet1(str: String?): String = str?.let { "我是有值的" } ?: "我是空值"



