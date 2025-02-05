package com.androidlearning.materialtest.util_function

import android.content.Context
import android.widget.Toast

/**
 * =================================================================================================
 * == 简化 Toast
 * =================================================================================================
 */
fun String.showToast(context: Context, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, this, duration).show()
}

fun Int.showToast(context: Context, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, this, duration).show()
}


//fun main() {
//
//    val str = "2"
//    str.showToast(context)
//
//    val resId = 2
//    resId.showToast(context)
//
//
//}