package com.androidlearning.androidthreadtest

import android.os.Bundle
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.os.Handler
import android.os.Message
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    val updateText = 1

    lateinit var textView: TextView

    /**
     * 使用 Handle 异步消息处理机制来实现 UI 更新
     */
    val handler = object : Handler(Looper.getMainLooper()) {

        override fun handleMessage(msg: Message) {
            when (msg.what) {
                updateText -> textView.text = "Nice to meet you"
            }
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


        textView = findViewById(R.id.textView)
        val changeTextBtn: Button = findViewById(R.id.changeTextBtn)

        changeTextBtn.setOnClickListener {
            thread {
                // textView.text = "Nice to meet you"

                val msg = Message()
                msg.what = updateText
                // 发送 message 消息
                handler.sendMessage(msg)
            }
        }
    }
}