package com.example.uicustomviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class TitleLayout(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    init {
        LayoutInflater.from(context).inflate(R.layout.title, this)
        val back: Button = findViewById(R.id.back)
        back.setOnClickListener {
            val activity = context as AppCompatActivity
            activity.finish()
        }

        val edit: Button = findViewById(R.id.edit)
        edit.setOnClickListener {
            Toast.makeText(context, "edit", Toast.LENGTH_SHORT).show()
        }
    }
}