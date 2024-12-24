package com.androidlearning.sharedpreferencestest

import android.content.Context
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

        val saveButton: Button = findViewById(R.id.saveButton)
        val restoreButton: Button = findViewById(R.id.restoreButton)

        saveButton.setOnClickListener {
            val editor = getSharedPreferences("data", Context.MODE_PRIVATE).edit()
            editor.putString("name", "Tom")
            editor.putInt("age", 28)
            editor.putBoolean("married", false)
            editor.apply()

            Toast.makeText(this, "数据保存成功", Toast.LENGTH_SHORT).show()
        }

        restoreButton.setOnClickListener {
            val editor = getSharedPreferences("data", Context.MODE_PRIVATE)
            val name = editor.getString("name", "xj")
            val age = editor.getInt("age", 29)
            val married = editor.getBoolean("married", true)

            Log.d("MainActivity", "name is $name")
            Log.d("MainActivity", "age is $age")
            Log.d("MainActivity", "married is $married")

            Toast.makeText(this, "读取数据成功", Toast.LENGTH_SHORT).show()
        }
    }
}