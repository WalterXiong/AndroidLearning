package com.example.helloworld.learningkotlin.lambda

class LambdaTest {

}

fun main() {

    /**
     * list
     */
    // 不可变集合 list
    val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape")
    for (s in list) {
        println(s)
    }

    println("////////////")

    // 可变集合 list
    val mutableList = mutableListOf("Apple", "Banana", "Orange", "Pear", "Grape")
    mutableList.add("watermelon")
    for (s in mutableList) {
        println(s)
    }


    /**
     * set
     */
    // 不可变集合 set
    val set = setOf("Apple", "Banana", "Orange", "Pear", "Grape")

    // 可变集合 set
    val mutableSet = mutableSetOf("Apple", "Banana", "Orange", "Pear", "Grape")

    /**
     * hashmap
     */
    val oldMap = HashMap<String, Int>()
    oldMap.put("Apple", 1)
    oldMap["Banana"] = 2

    // 不可变 map
    val map = mapOf("Apple" to 1, "Banana" to 2, "Orange" to 3, "Pear" to 4, "Grape" to 5)

    // 可变 map
    val mutableMap =
        mutableSetOf("Apple" to 1, "Banana" to 2, "Orange" to 3, "Pear" to 4, "Grape" to 5)

    for (entry in map) {
        val key = entry.key
        val value = entry.value
        println("name is " + key + " number is " + value)
    }

    println("////////////")

    for ((key, value) in map) {
        println("name is " + key + " number is " + value)
    }
}
