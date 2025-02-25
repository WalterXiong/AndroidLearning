package com.androidlearning.flowtest

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val textView = findViewById<TextView>(R.id.text_view)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {

            // 由于 collect 是一个挂起函数，所以只能在协程作用域中才能运行
            lifecycleScope.launch {

                // 只要调用了collect函数之后就相当于进入了一个死循环，它的下一行代码是永远都不会执行到的。
                // 因此，如果你的代码中有多个Flow需要collect，正确的写法应该是借助launch函数再启动子协程去collect，这样不同子协程之间就互不影响了
                launch {
                    mainViewModel.timeFlow.collect { time ->
                        textView.text = time.toString()
                        Log.d("FlowTest", "Update time $time in UI.")
                    }
                }

                launch {
                    // 流速不均匀问题，使用 collectLatest
                    mainViewModel.timeFlow.collectLatest { time ->
                        textView.text = time.toString()
                        delay(3000)
                        Log.d("FlowTest", "Update time $time in UI.")
                    }
                }

            }


        }
    }
}