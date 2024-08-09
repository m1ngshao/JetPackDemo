package com.example.jetpackdemo.kotlinLearn

fun main() {
    val mlist = listOf(1,2,3,4,5)

    for(item : Int in mlist){
        println("item:$item")
    }

    mlist.forEach {
        println(it)
    }

    mlist.forEachIndexed { index, i ->
        println("下标：$index,元素：$i")
    }
    val list = listOf("名少","张德华","段黎明")
    val(value1 :String,value2 : String,value3 : String) = list
    //_拒收
    val(_:String,v2:String,v3:String) = list

    val set = setOf<Int>(1,2,3,4,5,6,7,8,9,9,9,99,9)
    println(set)
    var set1 =  set.toMutableSet()
    set1 += 10
    println(set1)

    val list1 = listOf<Int>(1,2,3,4,5,6,7,8,9,9,9,99,9)
    val set2 =  list1.toSet()
    println(set2)
    val list2 = list1.toSet().toList()
    println(list2)
    val list3 = list1.distinct()
    println(list3)
    val list4 = list1.toMutableList()

    val map1 : Map<String,Double> = mapOf("ming" to 545.4,"da" to (399.9))
    val map2 : Map<String,Double> = mapOf(Pair("ming",666.9), Pair("da",8888.6))

    println(map1["ming"])
    println(map1.getOrDefault("ming",-1))
    println(map2.getOrElse("ming") { -1 })
    map1.forEach{
        println("key:${it.key}, value:${it.value}")
    }
    map1.forEach{key:String,value:Double ->
        println("key : $key,value :$value")
    }
    map2.forEach {(key:String,value:Double)->
        println("key:$key,value:$value")
    }
    for(item:Map.Entry<String,Double> in map2){
        println("key:${item.key},value:${item.value}")
    }
}