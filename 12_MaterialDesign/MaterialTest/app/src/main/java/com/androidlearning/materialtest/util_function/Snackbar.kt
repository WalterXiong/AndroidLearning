package com.androidlearning.materialtest.util_function

import android.view.View
import com.google.android.material.snackbar.Snackbar

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