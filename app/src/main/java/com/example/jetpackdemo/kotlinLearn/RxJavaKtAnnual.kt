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

    create1 {
        "mingshao"
        123
        true
        "AAAAAAAAAA"
        5452.23
    }.map {
        "name"
    }.map {
        123.23
    }.observer1 {
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

class RxJavaCoreClassObject1<T> (var valueItem: T)

inline fun <INPUT,OUTPUT>RxJavaCoreClassObject1<INPUT>.map(action: INPUT.() -> OUTPUT) : RxJavaCoreClassObject1<OUTPUT>{
    val mapResult = action(valueItem)
    return RxJavaCoreClassObject1(mapResult)
}

inline fun <CREATE_OUTPUT>create1(createLambda: () -> CREATE_OUTPUT) : RxJavaCoreClassObject1<CREATE_OUTPUT>{
    val createResult : CREATE_OUTPUT = createLambda()
    return RxJavaCoreClassObject1(createResult)
}

inline fun <OBSERVERINPUT>RxJavaCoreClassObject1<OBSERVERINPUT>.observer1(observerAction: OBSERVERINPUT.() -> Unit){
    observerAction(valueItem)
}
