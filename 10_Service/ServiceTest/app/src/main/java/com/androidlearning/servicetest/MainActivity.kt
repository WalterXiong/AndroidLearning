package com.androidlearning.servicetest

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var downloadBinder: MyService.DownloadBinder

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(
            name: ComponentName?,
            service: IBinder?
        ) {
            downloadBinder = service as MyService.DownloadBinder
            downloadBinder.startDownload()
            downloadBinder.getProgress()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            TODO("Not yet implemented")
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val starServiceBtn: Button = findViewById(R.id.startServiceBtn)
        val stopServiceBtn: Button = findViewById(R.id.stopServiceBtn)

        starServiceBtn.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            startService(intent)
        }

        stopServiceBtn.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            stopService(intent)
        }


        val bindServiceBtn: Button = findViewById(R.id.bindServiceBtn)
        val unbindServiceBtn: Button = findViewById(R.id.unbindServiceBtn)
        val testBtn: Button = findViewById(R.id.testBtn)

        bindServiceBtn.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            // 绑定 Service
            bindService(intent, connection, BIND_AUTO_CREATE)
        }

        testBtn.setOnClickListener {
            // 调用 service binder 中的方法
            val progress = downloadBinder.getProgress()
            Toast.makeText(this, "进度：$progress", Toast.LENGTH_SHORT).show()
        }


        unbindServiceBtn.setOnClickListener {
            // 解绑 Service
            unbindService(connection)
        }


        val startIntentServiceBtn = findViewById<Button>(R.id.startIntentServiceBtn)
        startIntentServiceBtn.setOnClickListener {
            // 打印主线程的id
            Log.d("MyIntentService", "Thread id is ${Thread.currentThread().name}")
            val intent = Intent(this, MyIntentService::class.java)
            startService(intent)
        }

    }
}