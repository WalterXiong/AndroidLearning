package com.example.helloworld.learningkotlin.class_and_extensive

import com.example.helloworld.learningkotlin.interface_study.Study

class Student(val sno: String, val grade: Int, name: String, age: Int) : Person(name, age) {

    init {
        println("sno is " + sno)
        println("grade is " + grade)
    }

    //    val sno = ""
    //    val grade = 0

}

/**
 * 次级构造函数
 */
class Student1(val no: Int, val gender: String, name: String, age: Int) : Person(name, age) {

    constructor(name: String, age: Int) : this(1, "man", name, age) {

    }

    constructor() : this("Tom", 28) {

    }
}

/**
 * 接口的实现
 */
// 普通版本
class Student2(name: String, age: Int) : Person(name, age), Study {

    override fun readBooks() {
        println(name + " is reading !")
    }

    override fun doHomeWork() {
        println(name + " is doing homework !")
    }

}

// 接口函数有默认实现版本
class Student3(name: String, age: Int) : Person(name, age), Study {

    override fun readBooks() {
        println(name + " is reading !")
    }

}


fun main() {

    val student = Student2("ed", 22)

    val defImplStudent = Student2("json", 12)

//    doStudy(student)
    doStudy(defImplStudent)

}

fun doStudy(study: Study) {
    study.readBooks()
    study.doHomeWork()
}
