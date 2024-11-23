package com.example.helloworld.learningkotlin

class LearnKotlinLogicControl {

}

fun main() {

//    val a: Int = 43
//    val b: Int = 22
//    val value = lagerNumber(a, b)
//    println("larger number is : " + value)

    checkNumber(29L)

    /**
     * 循环
     */
    // circular()

    for (i in 0..10) {
        print(i) // 012345678910

    }
    print("\n")

    for (i in 0 until 10) {
        print(i) // 0123456789
    }
    print("\n")

    for (i in 0 until 10 step 2) {
        print(i) // 02468
    }
    print("\n")

}

/**
 * if 条件判断
 */
fun lagerNumberByIf(num1: Int, num2: Int): Int {

    var value = 0
    if (num1 > num2) {
        value = num1
    } else {
        value = num2
    }
    return value
}


// if 允许有返回值，返回值为每个条件块中最后的一行代码
fun lagerNumberByIf1(num1: Int, num2: Int): Int {

    val value = if (num1 > num2) {
        num1
    } else {
        num2
    }
    return value
}

fun lagerNumberByIf2(num1: Int, num2: Int): Int {

    return if (num1 > num2) {
        num1
    } else {
        num2
    }
}

fun lagerNumberByIf3(num1: Int, num2: Int): Int = if (num1 > num2) {
    num1
} else {
    num2
}

fun lagerNumberByIf4(num1: Int, num2: Int) = if (num1 > num2) num1 else num2

// 输入学生姓名，获得成绩 if 编写
fun getScoreByIf(name: String): Int = if (name == "Tom") {
    96
} else if (name == "Jack") {
    87
} else if (name == "Jim") {
    60
} else if (name == "Lily") {
    100
} else{
    0
}


/**
 * when 条件语句
 */
// 精确匹配
fun getScoreByWhen(name: String) = when (name) {
    "Tom" -> 96
    "Jack" -> 87
    "Jim" -> 60
    "Lily" -> 100
    else -> 0
}

// 类型判断
fun checkNumber(num: Number) = when(num) {
    is Int -> println(num.toString() + " is Int ")
//    is Long -> println(num.toString() + " is Long ")
    is Float -> println(num.toString() + " is Float ")
    is Double -> println(num.toString() + " is Double ")
    is Byte -> println(num.toString() + " is Byte ")
    else -> println(num.toString() + " not support ")
}

/**
 * =================================================================================================
 * 循环语句
 *
 * 区间：
 *      0..10       --> [0,10]      0 <=.. <= 10
 *      0 until 10  --> [0,10)      0 <=.. < 10
 *      0 until 10  step 2          步长为 2
 *
 *      10 downTo 0 --> [10,0]      0 >=.. <= 10    降区间
 */

fun circular() {

    var i: Int = 10

    while (i >= 0) {
        println(i--)
    }
}

