package com.example.helloworld.learningkotlin.can_null_or_notnull_value

import com.example.helloworld.learningkotlin.interface_study.Study

/**
 * 2.7 空指针检查 -- 2.7.2 可空值类型
 */
class CanNullOrNotNullValue {


}

var content: String? = "hello"

fun main() {

    if (content != null) {
        printUpperCase()
    }
}

fun printUpperCase() {

    content?.let { cont ->
        val upper: String = cont.uppercase()
        println(upper)
    }
}

//fun main() {
//
//    doStudy(null)
//}
//
//fun doStudy(study: Study?) {
//
//    study?.let { stu ->
//        stu.readBooks()
//        stu.doHomeWork()
//    }
//}
