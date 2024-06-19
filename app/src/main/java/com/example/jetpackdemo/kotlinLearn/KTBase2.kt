package com.example.jetpackdemo.kotlinLearn

fun main() {
    val str = "张明达"

    //具名函数调用
    val result1  = with(str,::getLen)
    val result2 = with(result1,::getLenInfoAction)
    val result3 = with(result2,::getInfoMap)
    with(result3,::show)

    //匿名调用
    with(with(with(with(str){
        length
    }){
        "你的字符串长度是$this"
    }){
        "[$this]"
    }){
        println(this)
    }

}
fun getLen(string: String) : Int = string.length
fun getLenInfoAction(len : Int) : String = "你的字符串长度是$len"
fun getInfoMap(info : String) : String = "[$info]"
fun show(str : String)  = println(str)