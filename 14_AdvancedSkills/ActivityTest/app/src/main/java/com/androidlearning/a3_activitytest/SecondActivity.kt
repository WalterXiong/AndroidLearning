package com.androidlearning.a3_activitytest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import com.androidlearning.a3_activitytest.BestActivityPrectice.BaseActivity

class SecondActivity : BaseActivity() {

    /**
     * 启动 activity 的最佳实践
     */
    companion object {

        fun actionStart(context: Context, data1: String, data2: String) {

            val intent = Intent(context, SecondActivity::class.java).apply {
                putExtra("param1", data1)
                putExtra("param2", data2)
            }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        Log.d("SecondActivity-s", "Task id is $taskId")

        setContentView(R.layout.second_laout)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        val extraData = intent.getStringExtra("扩展数据")
        Log.d("SecondActivity", "第一个 Activity 传过来的扩展数据是：$extraData")

        val button2 = findViewById<Button>(R.id.button2)
        button2.setOnClickListener {

            // resultIntent()

            /**
             * 启动模式测试
             */
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }

        // 注册返回按钮的回调
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                resultIntent()
            }
        })
    }

    private fun resultIntent() {
        val intent = Intent()
        intent.putExtra("返回数据", "你好，第一个 Activity")
        setResult(RESULT_OK, intent)
        finish()
    }


    /**
     * 已经被弃用了，OnBackPressedDispatcher 代替
     */
    override fun onBackPressed() {
        super.onBackPressed()

    }
}