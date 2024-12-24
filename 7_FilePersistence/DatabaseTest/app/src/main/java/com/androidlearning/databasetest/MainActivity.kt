package com.androidlearning.databasetest

import android.os.Bundle
import android.widget.Button
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

        // val myDatabaseHelper = MyDatabaseHelper(this, "BookStore.db", 1)

        // 升级数据库
        val myDatabaseHelper = MyDatabaseHelper(this, "BookStore.db", 2)

        createDatabase.setOnClickListener {
            myDatabaseHelper.writableDatabase
        }
    }
}