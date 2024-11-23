package com.example.broadcastbestpractice

fun String.lettersCount(): Int {
    println("你调用了 String.lettersCount() 扩展方法")
    var count = 0
    for (char in this) {
        if (char.isLetter()) {
            count++
        }
    }
    return count
}