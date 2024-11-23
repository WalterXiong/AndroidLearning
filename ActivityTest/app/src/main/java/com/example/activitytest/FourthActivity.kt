package com.example.activitytest

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class FourthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.fourth_layout)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        val extras: Bundle? = intent.extras
        val name = extras?.getString("name")
        val age = extras?.getInt("age")
        Log.e("FourthActivity", "extra_data is $name , $age")

        val data = intent.getStringExtra("extra_data")
        Log.d("FourthActivity", "extra_data is $data")

        val button4: Button = findViewById(R.id.button4)

        button4.setOnClickListener {
            Log.d("FourthActivity", "You clicked button4")

            /**
             * Intent 回传参数给调用它的页面
             */
            val intent: Intent = Intent()
            // 设置回传的参数
            intent.putExtra("data_return", "Hello FirstActivity")
            // 设置回传状态
            setResult(RESULT_OK, intent)
            // 销毁这个 activity 才能将参数传回给调用它的 activity
            finish()
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
//        super.onBackPressed()
        Log.d("FourthActivity", "You clicked back")
        val intent = Intent()
        intent.putExtra("data_return", "Hello FirstActivity, back")
        setResult(RESULT_OK, intent)
        finish()
    }
}