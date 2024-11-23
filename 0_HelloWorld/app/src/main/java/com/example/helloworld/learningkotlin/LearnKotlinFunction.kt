package com.example.helloworld.learningkotlin

import kotlin.math.max

class LearnKotlinFunction {
}

fun main() {

    val a: Int = 43
    val b: Int = 22
    val value = lagerNumber(a, b)
    println("larger number is : " + value)

}

fun lagerNumber(num1: Int, num2: Int): Int {
    return max(num1, num2)
}

// 方法体只有一行代码时，可以使用该语法糖
fun lagerNumber1(num1: Int, num2: Int) = max(num1, num2)