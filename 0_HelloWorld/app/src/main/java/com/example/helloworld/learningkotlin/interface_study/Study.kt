package com.example.helloworld.learningkotlin.interface_study

import androidx.compose.runtime.sourceInformation

interface Study {

    fun readBooks()

    fun doHomeWork(){
        println("do homework default implementation.")
    }
}