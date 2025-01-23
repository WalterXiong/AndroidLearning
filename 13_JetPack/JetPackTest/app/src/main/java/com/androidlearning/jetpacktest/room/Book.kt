package com.androidlearning.jetpacktest.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Book(var name: String, var pages: Int, var author: String) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}