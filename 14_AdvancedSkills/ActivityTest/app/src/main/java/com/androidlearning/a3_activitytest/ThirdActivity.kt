package com.androidlearning.a3_activitytest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import com.androidlearning.a3_activitytest.BestActivityPrectice.ActivityCollector
import com.androidlearning.a3_activitytest.BestActivityPrectice.BaseActivity

class ThirdActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        Log.d("ThirdActivity-s", "Task id is $taskId")

        setContentView(R.layout.third_layout)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        val button3 = findViewById<Button>(R.id.button3)
        button3.setOnClickListener {
            ActivityCollector.finishAll()
        }
    }
}