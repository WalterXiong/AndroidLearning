package com.example.helloworld.learningkotlin.class_and_extensive


fun main() {

//    val p = Person()
//    p.name = "xj"
//    p.age = 28
//    p.eat()
}

// 加上 open 关键字后表示该类可以被继承
open class Person(val name: String, val age: Int) {

    fun eat() {
        println(name + " is eating. He is " + age + " years old.")
    }

}