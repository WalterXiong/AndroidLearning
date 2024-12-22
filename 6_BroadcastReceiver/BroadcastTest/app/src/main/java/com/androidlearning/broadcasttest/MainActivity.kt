package com.androidlearning.broadcasttest

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var timeChangeReceiver: TimeChangeReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        /**
         * 动态注册广播
         */
        // val intentFilter = IntentFilter()
        // intentFilter.addAction("android.intent.action.TIME_TICK")
        // timeChangeReceiver = TimeChangeReceiver()
        // registerReceiver(timeChangeReceiver, intentFilter)


        /**
         * 发送自定义广播
         */
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {

            val intent = Intent("com.walter.broadcasttest.MY_BROADCAST")
            intent.setPackage(packageName)

            // 发送标准广播
            // sendBroadcast(intent)

            // 发送有序广播
            sendOrderedBroadcast(intent, null)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(timeChangeReceiver)
    }

    inner class TimeChangeReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {
            Toast.makeText(context, "时间改变了", Toast.LENGTH_SHORT).show()
        }
    }
}