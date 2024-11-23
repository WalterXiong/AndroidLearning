package com.example.helloworld.learningkotlin.lambda

class LambdaComprehension {
}

fun main() {


    val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape")

//    list.maxBy({ fruit: String -> fruit.length })

//    list.maxBy() { fruit: String -> fruit.length }

//    list.maxBy { fruit: String -> fruit.length }

//    list.maxBy { fruit -> fruit.length }

    list.maxBy { it.length }


    val newList = list
        .filter { it.length <= 5 }
        .map { it.uppercase() }

    for (fruit in newList) {
        println(fruit)
    }


    val fruitList = listOf("Apple", "Banana", "Orange", "Pear", "Grape", "Watermelon")

    val anyResult = fruitList.any { it.length >= 5 }

    val allResult = fruitList.all { it.length >= 5 }

    println("anyResult : " + anyResult + "\nallResult : " + allResult)

    Thread { println("run run run"); }

}