package com.androidlearning.databasetest

import android.annotation.SuppressLint
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val createDatabase: Button = findViewById(R.id.createDatabase)

        // 创建数据库
        // val myDatabaseHelper = MyDatabaseHelper(this, "BookStore.db", 1)

        // 升级数据库
        val dbHelper = MyDatabaseHelper(this, "BookStore.db", 2)
        createDatabase.setOnClickListener {
            dbHelper.writableDatabase
        }


        val addData: Button = findViewById(R.id.addData)
        val deleteData: Button = findViewById(R.id.deleteData)
        val updateData: Button = findViewById(R.id.updateData)
        val queryData: Button = findViewById(R.id.queryData)
        val replaceData: Button = findViewById(R.id.replaceData)

        // 添加数据
        addData.setOnClickListener {
            val db = dbHelper.writableDatabase

            var book1 = ContentValues().apply {
                put("name", "布布宝历险记")
                put("author", "Walterxiong")
                put("pages", 555)
                put("price", 19.99)
            }
            db.insert("Book", null, book1)

            var book2 = ContentValues().apply {
                put("name", "The Da Vinci Code")
                put("author", "Dan Brown")
                put("pages", 454)
                put("price", 16.66)
            }
            db.insert("Book", null, book2)
        }

        // 删除数据
        deleteData.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.delete("Book", "name = ?", arrayOf("The Da Vinci Code"))
            Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show()
        }

        // 更新数据
        updateData.setOnClickListener {
            val db = dbHelper.writableDatabase
            val value = ContentValues()
            value.put("price", 10.99)
            db.update("Book", value, "name = ?", arrayOf("The Da Vinci Code"))
            Toast.makeText(this, "更新成功", Toast.LENGTH_SHORT).show()
        }

        queryData.setOnClickListener {
            val db = dbHelper.writableDatabase
            var cursor = db.query("Book", null, null, null, null, null, null)
            if (cursor.moveToFirst()) {
                do {
                    // 遍历Cursor对象，取出数据并打印
                    val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                    val author = cursor.getString(cursor.getColumnIndexOrThrow("author"))
                    val pages = cursor.getInt(cursor.getColumnIndexOrThrow("pages"))
                    val price = cursor.getDouble(cursor.getColumnIndexOrThrow("price"))
                    Log.d("MainActivity", "book name is $name")
                    Log.d("MainActivity", "book author is $author")
                    Log.d("MainActivity", "book pages is $pages")
                    Log.d("MainActivity", "book price is $price")
                } while (cursor.moveToNext())
            }
            cursor.close()

            Toast.makeText(this, "查询成功", Toast.LENGTH_SHORT).show()
        }

        /**
         * 使用事务
         */
        replaceData.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.beginTransaction()

            try {
                db.delete("Book", null, null)
                if (true) {
                    // 手动抛出异常
                    throw NullPointerException()
                }
                val value = ContentValues().apply {
                    put("name", "Xiaomi SU7")
                    put("author", "Lei Jun")
                    put("pages", 720)
                    put("price", 20.59)
                }
                db.insert("Book", null, value)
                db.setTransactionSuccessful()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                db.endTransaction()
            }
        }
    }
}