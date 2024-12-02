package com.androidlearning.uiwidgettest

import android.icu.lang.UCharacter.GraphemeClusterBreak.V
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity(), OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener(this)
//        button.setOnClickListener {
//            Toast.makeText(this, "你点击了按钮", Toast.LENGTH_SHORT).show()
//        }


    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button -> {

                val editText = findViewById<EditText>(R.id.editText)
                val content = editText.text.toString()
                Toast.makeText(this, "你点击了按钮：$content", Toast.LENGTH_SHORT).show()


//                val imageView = findViewById<ImageView>(R.id.imageView)
//                imageView.setImageResource(R.drawable.ic_launcher_foreground)

                val progressBar = findViewById<ProgressBar>(R.id.progressBar)
                if (progressBar.visibility == View.VISIBLE) {
                    progressBar.visibility = View.GONE
                } else {
                    progressBar.visibility = View.VISIBLE
                }

                progressBar.progress += 10


                AlertDialog.Builder(this).apply {
                    setTitle("这是一个对话框")
                    setMessage("这是对话框中的消息")
                    setCancelable(false)
                    setPositiveButton("确定") { dialog, which ->

                    }
                    setNegativeButton("取消") { dialog, which ->

                    }
                    show()
                }
            }
        }
    }
}