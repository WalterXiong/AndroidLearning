package com.example.sharedpreferencestest

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity_layout)

        val saveButton: Button = findViewById(R.id.saveButton)

        saveButton.setOnClickListener {
            val sharedPreferences = getSharedPreferences("data", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("name", "walter")
            editor.putInt("age", 28)
            editor.putBoolean("married", true)
            editor.apply()
        }

        val restoreButton: Button = findViewById(R.id.restoreButton)
        restoreButton.setOnClickListener {
            val sharedPreferences = getSharedPreferences("data", MODE_PRIVATE)
            val name = sharedPreferences.getString("name", "")
            val age = sharedPreferences.getInt("age", 0)
            val married = sharedPreferences.getBoolean("married", true)

            Log.i("MainActivity", "name is: $name")
            Log.i("MainActivity", "age is: $age")
            Log.i("MainActivity", "married is: $married")
        }
    }
}
