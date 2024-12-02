package com.androidlearning.uicustomviews

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet.Layout
import androidx.resourceinspection.annotation.Attribute

/**
 * 自定义控件
 */
class TitleLayout(context: Context, attribute: AttributeSet) : LinearLayout(context, attribute) {

    init {
        LayoutInflater.from(context).inflate(R.layout.title, this)

        val back = findViewById<Button>(R.id.titleBack)
        back.setOnClickListener {
            val activity = context as Activity
            activity.finish()
        }

        val edit = findViewById<Button>(R.id.titleEdit)
        edit.setOnClickListener {
            Toast.makeText(context, "你点击了右上角菜单", Toast.LENGTH_SHORT).show()
        }
    }

}