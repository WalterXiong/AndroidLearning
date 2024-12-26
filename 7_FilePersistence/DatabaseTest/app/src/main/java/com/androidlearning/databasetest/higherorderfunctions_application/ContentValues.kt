package com.androidlearning.databasetest.higherorderfunctions_application

import android.content.ContentValues

//fun cvOf(vararg pairs: Pair<String, Any?>): ContentValues {
//
//    val cv = ContentValues()
//
//    for (pair in pairs) {
//
//        var key = pair.first
//        var value = pair.second
//
//        when (value) {
//            is Short -> cv.put(key, value)
//            is Int -> cv.put(key, value)
//            is Long -> cv.put(key, value)
//            is Float -> cv.put(key, value)
//            is Double -> cv.put(key, value)
//            is Boolean -> cv.put(key, value)
//            is String -> cv.put(key, value)
//            is Byte -> cv.put(key, value)
//            is ByteArray -> cv.put(key, value)
//            null -> cv.putNull(key)
//        }
//    }
//
//    return cv
//}

fun cvOf(vararg pairs: Pair<String, Any?>) = ContentValues().apply {

    for (pair in pairs) {

        var key = pair.first
        var value = pair.second

        when (value) {
            is Short -> put(key, value)
            is Int -> put(key, value)
            is Long -> put(key, value)
            is Float -> put(key, value)
            is Double -> put(key, value)
            is Boolean -> put(key, value)
            is String -> put(key, value)
            is Byte -> put(key, value)
            is ByteArray -> put(key, value)
            null -> putNull(key)
        }
    }
}