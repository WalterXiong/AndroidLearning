package com.androidlearning.retrofittest.retrofit_best_practice

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.jvm.java

object ServiceCreator {

    private const val BASE_URL = "http://192.168.31.125/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    inline fun <reified T> create(): T = create(T::class.java)

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)
}