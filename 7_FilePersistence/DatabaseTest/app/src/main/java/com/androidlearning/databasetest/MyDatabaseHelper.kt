package com.androidlearning.databasetest

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class MyDatabaseHelper(val context: Context, val name: String, val version: Int) :
    SQLiteOpenHelper(context, name, null, version) {

    private val createBook = """
        create table Book (
            id integer primary key autoincrement,
            author text,
            price real,
            pages integer,
            name text 
        )"""

    private val createCategory = """
        create table Category (
            id integer primary key autoincrement,
            category_name text,
            category_code integer
        )"""

    /**
     * 在这里执行建表语句
     */
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createBook)
        db?.execSQL(createCategory)
        Toast.makeText(context, "创建成功", Toast.LENGTH_SHORT).show()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.let {
            it.execSQL("drop table if exists Book")
            it.execSQL("drop table if exists Category")
            onCreate(db)
        }

    }
}