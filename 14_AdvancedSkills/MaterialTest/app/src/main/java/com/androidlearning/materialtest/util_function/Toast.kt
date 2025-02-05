package com.androidlearning.materialtest.util_function

import android.widget.Toast
import com.androidlearning.materialtest.MyApplication

/**
 * =================================================================================================
 * == 简化 Toast
 * =================================================================================================
 */
fun String.showToast(duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(MyApplication.context, this, duration).show()
}

fun Int.showToast(duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(MyApplication.context, this, duration).show()
}


fun main() {

    val str = "2"
    str.showToast()

    "This is Toast".showToast()

    val resId = 2
    resId.showToast()
}