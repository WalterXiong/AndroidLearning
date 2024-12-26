package com.androidlearning.databasetest.higherorderfunctions_application

import android.content.SharedPreferences

fun SharedPreferences.open(block: SharedPreferences.Editor.() -> Unit) {
    var edit = edit()
    edit.block()
    edit.apply()
}