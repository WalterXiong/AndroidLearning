package com.androidlearning.a3_activitylifecycletest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private val tag = "MainActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag, "onCreate")

        enableEdgeToEdge()
        setContentView(R.layout.main_layout)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }


        savedInstanceState?.let {
            val tempData = it.getString("data_key")
            Log.d(tag, "tempData is $tempData")
        }


        val startNormalActivity = findViewById<Button>(R.id.startNormalActivity)
        val startDialogActivity = findViewById<Button>(R.id.startDialogActivity)

        startNormalActivity.setOnClickListener {
            val intent = Intent(this, NormalActivity::class.java)
            startActivity(intent)
        }
        startDialogActivity.setOnClickListener {
            val intent = Intent(this, DialogActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val tempData = "Something you just typed"
        outState.putString("data_key", tempData)
    }

    override fun onStart() {
        super.onStart()
        Log.d(tag, "onStart (调用 onStart() 方法)")
    }

    override fun onResume() {
        super.onResume()
        Log.d(tag, "onResume (调用 onResume() 方法)")
    }

    override fun onPause() {
        super.onPause()
        Log.d(tag, "onPause (调用 onPause() 方法)")
    }

    override fun onStop() {
        super.onStop()
        Log.d(tag, "onStop (调用 onStop() 方法)")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "onDestroy (调用 onDestroy() 方法)")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(tag, "onRestart (调用 onRestart() 方法)")
    }
}