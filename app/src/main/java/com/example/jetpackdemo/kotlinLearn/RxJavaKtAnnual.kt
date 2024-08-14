package com.example.jetpackdemo.kotlinLearn

fun main() {
    create {
        "mingshao"
        123
        true
        "AAAAAAAAAA"
        5452.23f
    }.map {
        "mingshao"
        123
        true
        "AAAAAAAAAA"
        "你的值是：$this"
    }.map {
        "[$this]"
    }.map {
        "@@$this@@"
    }.observer {
        println("最终消费：$this")
    }
}
class RxJavaCoreClassObject<T> (var valueItem : T)

inline fun <INPUT,OUTPUT>RxJavaCoreClassObject<INPUT>.map(mapAction : INPUT.() -> OUTPUT) : RxJavaCoreClassObject<OUTPUT>{
    val mapResult = mapAction(valueItem)
    return RxJavaCoreClassObject(mapResult)
}

inline fun <CREATE_OUTPUT>create(createLambda : () -> CREATE_OUTPUT) : RxJavaCoreClassObject<CREATE_OUTPUT>{
    val createResult : CREATE_OUTPUT = createLambda()

    return RxJavaCoreClassObject(createResult)
}

inline fun <OBSERVERINPUT>RxJavaCoreClassObject<OBSERVERINPUT>.observer(observerAction : OBSERVERINPUT.() -> Unit){
    observerAction(valueItem)
}