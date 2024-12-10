package com.androidlearning.uibestpractice

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), OnClickListener {

    private val msgList = ArrayList<MsgEntity>()

    private lateinit var adapter: MsgAdapter

    private lateinit var inputText: EditText

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initMsg()

        if (!::inputText.isInitialized) {
            inputText = findViewById(R.id.inputText)
        }

        if (!::recyclerView.isInitialized) {
            recyclerView = findViewById(R.id.recyclerView)
        }

        val sendButton = findViewById<Button>(R.id.send)
        sendButton.setOnClickListener(this)

        val layoutManager = LinearLayoutManager(this)

        recyclerView.layoutManager = layoutManager

        if (!::adapter.isInitialized) {
            adapter = MsgAdapter(msgList)
        }

        recyclerView.adapter = adapter
    }

    override fun onClick(v: View?) {
        v?.let {

            when (it.id) {
                R.id.send -> {

                    val content = inputText.text.toString()
                    if (content.isNotEmpty()) {
                        val msg = MsgEntity(content, MsgEntity.TYPE_SENT)
                        msgList.add(msg)

                        // 当有新消息时，刷新RecyclerView中的显示 ( 下标 )
                        adapter.notifyItemInserted(msgList.size - 1)

                        // 将RecyclerView 定位到最后一行 ( 下标 )
                        recyclerView.scrollToPosition(msgList.size - 1)

                        // 初始化输入框
                        inputText.setText("")
                    }
                }
            }

        }
    }

    private fun initMsg() {
        val msg1 = MsgEntity("你好，bro！", MsgEntity.TYPE_RECEIVED)
        msgList.add(msg1)
        val msg2 = MsgEntity("你好. 这是谁? ", MsgEntity.TYPE_SENT)
        msgList.add(msg2)
        val msg3 = MsgEntity("这是汤姆. 很高兴与你交谈. ", MsgEntity.TYPE_RECEIVED)
        msgList.add(msg3)
    }

}