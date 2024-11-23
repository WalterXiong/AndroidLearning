package com.example.helloworld.learningkotlin.data_and_single_class

data class CallPhone(val brand: String, val price: Double)

fun main() {

    val phone1 = CallPhone("xiaomi", 4599.0)
    val phone2 = CallPhone("xiaomi", 4599.0)

    println("phone1 equals phone2 " + (phone1 == phone2))
}