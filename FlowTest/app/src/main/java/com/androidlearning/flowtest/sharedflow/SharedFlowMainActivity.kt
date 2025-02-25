package com.androidlearning.flowtest.sharedflow

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.androidlearning.flowtest.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * SharedFlow 的基本用法
 */
class SharedFlowMainActivity : AppCompatActivity() {

    private val mainViewModel by viewModels<SharedFlowViewModel>()

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
            /**
             * 粘性 flow
             */
            /*lifecycleScope.launch {

                repeatOnLifecycle(Lifecycle.State.STARTED) {

                    mainViewModel.clickCountFlow.collect { item ->
                        textView.text = it.toString()
                    }
                }
            }*/

            /**
             * 非粘性 flow
             */
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    mainViewModel.loginFlow.collectLatest { item ->
                        if (item.isNotBlank()) {
                            Toast
                                .makeText(this@SharedFlowMainActivity, item, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }

        }
    }
}