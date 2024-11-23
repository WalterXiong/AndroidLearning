package com.example.broadcastbestpractice

class HigherOrderFunction {

}


fun main() {
//    val num1: Int = 100
//    val num2: Int = 80
//
//    val val1 = num1AndNum2(100, 80, ::plus)
//    val val2 = num1AndNum2(100, 80, ::minus)
//
//    println(val1)
//    println(val2)
//
//    val val3 = num1AndNum2(100, 80) { n1, n2 -> n1 + n2 }
//    val val4 = num1AndNum2(100, 80) { n1, n2 -> n1 - n2 }
//
//    println(val3)
//    println(val4)


    // ============================================================================================

//    val list = listOf("1", "2", "3")
//
//    StringBuilder().build {
//        append("start add list number")
//        for (s in list) {
//            append(s + "add")
//        }
//        append("end add list number")
//    }

    // ============================================================================================

//    inlineTest(
//        // 内联函数的 lambda
//        {
//            println("start inline fun lambda inlineTest")
//            for (i in 0 until 10) {
//                println(i)
//            }
//            return
//            println("end inline fun lambda inlineTest")
//        },
//
//        // 非内联函数的 lambda
//        {
//            println("start inline fun lambda inlineTest")
//            for (i in 0 until 10) {
//                println(i)
//            }
//            return@inlineTest
//            println("end inline fun lambda inlineTest")
//        })


    println("main start")
    val str = ""
    printString(str) { s ->
        println("lambda start")
        if (s.isEmpty()) {
            return
        }
        println(s)
        println("lambda end")
    }
    println("main end")

}

fun plus(num1: Int, num2: Int): Int {
    return num1 + num2
}


fun minus(num1: Int, num2: Int): Int {
    return num1 - num2
}

inline fun num1AndNum2(num1: Int, num2: Int, operation: (Int, Int) -> Int): Int {
    val result = operation(num1, num2)
    return result
}

// ============================================================================================

fun StringBuilder.build(block: StringBuilder. () -> Unit): StringBuilder {
    // 执行函数类型参数
    block()
    return this
}

// ============================================================================================

inline fun inlineTest(block1: () -> Unit, noinline block2: () -> Unit) {
    block1()
    block2()

//    inlineTest2(block1, block2)
}


//fun inlineTest2(block1: () -> Unit, block2: () -> Unit) {
//    block1()
//    block2()
//}

inline fun printString(str: String, block: (String) -> Unit) {
    println("printString: begin")
    block(str)
    println("printString: end")
}


inline fun runRunnable(crossinline block: () -> Unit) {
    val runnable = Runnable {
        block()
    }
    runnable.run()
}




















