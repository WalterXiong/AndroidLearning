package com.example.uiwidgettest

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FirstActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var button1: Button
    private lateinit var editText: EditText
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.first_layout)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        button1 = findViewById(R.id.button)
        editText = findViewById(R.id.editText)
        progressBar = findViewById(R.id.progressBar)

        button1.setOnClickListener(this)

    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.button -> {
                val editTextValue = editText.text.toString()
                Toast.makeText(this, "You input value is $editTextValue", Toast.LENGTH_SHORT)
                    .show()

//                if (progressBar.visibility == View.VISIBLE) {
//                    progressBar.visibility = View.GONE
//                } else {
//                    progressBar.visibility = View.VISIBLE
//                }

                progressBar.progress += 10

                AlertDialog.Builder(this).apply {
                    setTitle("This is AlertDialog")
                    setMessage("You input value is $editTextValue")
                    setCancelable(false)
                    setPositiveButton("OK") { dialog, which ->

                    }
                    setNegativeButton("Cancel") { dialog, which ->

                    }
                    show()

                }
            }
        }
    }
}