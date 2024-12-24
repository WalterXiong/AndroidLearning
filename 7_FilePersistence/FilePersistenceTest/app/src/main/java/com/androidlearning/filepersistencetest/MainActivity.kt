package com.androidlearning.filepersistencetest

import android.content.Context
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        editText = findViewById(R.id.editText)

        val content = load()

        if (content.isNotEmpty()) {
            editText.setText(content)
            editText.setSelection(content.length)
            Toast.makeText(this, "恢复内容成功", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val inputStr = editText.text.toString()
        save(inputStr)
    }


    private fun save(inputText: String) {
        try {
            val outputStream = openFileOutput("data", Context.MODE_PRIVATE)
            val writer = BufferedWriter(OutputStreamWriter(outputStream))

            writer.use {
                it.write(inputText)
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }


    private fun load(): String {
        val content = StringBuilder()

        try {
            val inputStream = openFileInput("data")
            val reader = BufferedReader(InputStreamReader(inputStream))

            reader.use {
                reader.forEachLine {
                    content.append(it)
                }
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }

        return content.toString()
    }
}