package com.example.activitylifecycletest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.activitylifecycletest.ui.theme.ActivityLifeCycleTestTheme

class MainActivity : ComponentActivity() {

    private val tag: String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(tag, "onCreate()")

        setContentView(R.layout.main_layout)

        /**
         * activity 被回收的情况下保存临时数据
         */
        if (savedInstanceState != null) {
            val tempData = savedInstanceState.getString("data_key")
            Log.d(tag, "Temp data is $tempData")
        }

        val startNormalActivity: Button = findViewById(R.id.startNormalActivity)
        val startDialogActivity: Button = findViewById(R.id.startDialogActivity)

        startNormalActivity.setOnClickListener {
            val intent = Intent(this, NormalActivity::class.java)
            startActivity(intent)
        }

        startDialogActivity.setOnClickListener {
            val intent = Intent(this, DialogActivity::class.java)
            startActivity(intent)
        }

//        enableEdgeToEdge()
//        setContent {
//            ActivityLifeCycleTestTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//            }
//        }

    }

    override fun onStart() {
        super.onStart()
        Log.d(tag, "onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(tag, "onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(tag, "onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(tag, "onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "onDestroy()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(tag, "onRestart()")
    }

    // 保存临时数据
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val tempData = "Something you just typed"
        outState.putString("data_key", tempData)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ActivityLifeCycleTestTheme {
        Greeting("Android")
    }
}