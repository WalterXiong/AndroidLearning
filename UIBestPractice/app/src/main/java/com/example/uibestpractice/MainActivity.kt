package com.example.uibestpractice

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), OnClickListener {

    private val msgList = ArrayList<Msg>()

    private lateinit var send: Button

    private lateinit var inputText: EditText

    private lateinit var recyclerView: RecyclerView

    private lateinit var adapter: MsgAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        getMsg()

        recyclerView = findViewById(R.id.recyclerView)

        val layoutManager = LinearLayoutManager(this)

        recyclerView.layoutManager = layoutManager

        adapter = MsgAdapter(msgList)

        recyclerView.adapter = adapter

        inputText = findViewById(R.id.inputText)
        send = findViewById(R.id.send)

        send.setOnClickListener(this)
    }

    private fun getMsg() {
        msgList.add(Msg("你好，walter", Msg.TYPE_RECEIVED))
        msgList.add(Msg("你好呀，lucy", Msg.TYPE_SENT))
        msgList.add(Msg("师傅你是做什么工作的？", Msg.TYPE_RECEIVED))
    }

    override fun onClick(v: View?) {
        when (v) {
            send -> {
                val content = inputText.text.toString()
                if (content.isNotEmpty()) {
                    msgList.add(Msg(content, Msg.TYPE_SENT))
                    adapter.notifyItemInserted(msgList.size - 1)
                    recyclerView.scrollToPosition(msgList.size - 1)
                    inputText.setText("")
                }
            }
        }
    }
}