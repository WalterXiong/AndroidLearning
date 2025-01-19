package com.androidlearning.materialtest.util_function

import android.R.attr.text
import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

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


/**
 * =================================================================================================
 * == 简化 Snackbar
 * =================================================================================================
 */
fun View.showSnackBar(
    text: String,
    actionText: String? = null,
    duration: Int = Snackbar.LENGTH_SHORT,
    block: (() -> Unit)? = null
) {
    val bar = Snackbar.make(this, text, duration)
    if (actionText != null && block != null) {
        bar.setAction(actionText) {
            block()
        }
    }
    bar.show()
}

fun View.showSnackBar(
    resId: Int,
    actionText: String? = null,
    duration: Int = Snackbar.LENGTH_SHORT,
    block: (() -> Unit)? = null
) {
    val bar = Snackbar.make(this, resId, duration)
    if (actionText != null && block != null) {
        bar.setAction(actionText) {
            block()
        }
    }
    bar.show()
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