package com.androidlearning.a3_activitytest

import android.app.Activity
import android.app.ComponentCaller
import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.net.URL

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.first_layout)

        val button1: Button = findViewById(R.id.button1)

        // button1.setOnClickListener {
        // Toast.makeText(this, "你点击了 Button1 按钮", Toast.LENGTH_SHORT).show()
        // finish()
        // }


        /**
         * 注册一个 ActivityResult
         */
        val resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val resultIntent: Intent? = result.data
                resultIntent?.let {
                    val extraData = it.getStringExtra("返回数据")
                    Toast.makeText(this, "返回的数据为：$extraData", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        button1.setOnClickListener {

            // 显示 Intent
            // val intent: Intent = Intent(this, SecondActivity::class.java)

            // 隐式 Intent
            // val intent: Intent = Intent("com.androidlearning.a3_activitytest.ACTION_START")
            // intent.addCategory("com.androidlearning.a3_activitytest.MY_CATEGORY")
            // startActivity(intent)


            // 更多隐式Intent的用法
            // val intent: Intent = Intent(Intent.ACTION_VIEW)
            // intent.data = Uri.parse("https://www.baidu.com")
            // startActivity(intent)

            // val intent: Intent = Intent(Intent.ACTION_DIAL)
            // intent.data = Uri.parse("tel:10086")
            // startActivity(intent)

            /**
             * 向下一个Activity传递数据
             */
            // val data = "你好，第二个 Activity"
            // val intent: Intent = Intent(this, SecondActivity::class.java)
            // intent.putExtra("扩展数据", data)
            // startActivity(intent)

            val data = "你好，第二个 Activity"
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("扩展数据", data)
            resultLauncher.launch(intent)

        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?,
        caller: ComponentCaller
    ) {
        super.onActivityResult(requestCode, resultCode, data, caller)

    }


    /**
     * 创建菜单
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mian, menu)
        return true
    }

    /**
     * 给菜单添加响应事件
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_item -> Toast.makeText(this, "你点击了添加按钮", Toast.LENGTH_SHORT).show()
            R.id.remove_item -> Toast.makeText(this, "你点击了移除按钮", Toast.LENGTH_SHORT).show()
        }
        return true
    }
}
