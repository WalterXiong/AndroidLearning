package com.example.activitytest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 探究 activity 的 standard 模式
        Log.d("FirstActivity", this.toString())

        setContentView(R.layout.first_layout)

        val button1: Button = findViewById(R.id.button1)
        val textView1: TextView = findViewById(R.id.text_view1)

        button1.setOnClickListener {

            /**
             * 探究 activity 的 standard 模式
             */
//            val intent = Intent(this, SecondActivity::class.java)
//            startActivity(intent)

            // Toast.makeText(this, "You clicked Button1", Toast.LENGTH_SHORT).show()

            /**
             * 显示 Intent 使用
             */
            // val intent: Intent = Intent(this, SecondActivity::class.java)
            // startActivity(intent)

            /**
             * 隐式 Intent 的使用
             */
            // val hideIntent: Intent = Intent("com.example.activitytest.ACTION_START")
            // hideIntent.addCategory("com.example.activitytest.MY_CATEGORY")
            // startActivity(hideIntent)


            /**
             * 更多 Intent 的用法
             */
            // 调用浏览器打开网页
//            val intent = Intent(Intent.ACTION_VIEW)
//            intent.data = Uri.parse("http://www.google.com")
//            startActivity(intent)

            /**
             * Intent 参数传递
             */
            val intent = Intent(this, FourthActivity::class.java)
//            val data = "Hello FourthActivity!"
//            intent.putExtra("extra_data", data)
//            startActivity(intent)

            val bundle = Bundle()
            bundle.putString("name", "xj")
            bundle.putInt("age", 28)

            intent.putExtras(bundle)

            startActivity(intent)


            /**
             * Intent 参数传递 and 回传参数
             */
//            val intent = Intent(this, FourthActivity::class.java)
//            val data = "Hello FourthActivity!"
//            intent.putExtra("extra_data", data)
            /*
             * 第一个参: intent 本身
             * 第二个参: 请求码，一个唯一值即可，相当于标记该请求是我这个 Activity 发出去的
             */
//            startActivityForResult(intent, 1)

//            val myActivityLauncher =
//                registerForActivityResult(MyActivityResultContract()) { result ->
//                    Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT).show()
//                    textView1.text = "回传数据：$result"
//                }
//            myActivityLauncher.launch()

            // 销毁一个 Activity
            // finish()
        }
    }

    /**
     *
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {

            1 -> if (resultCode == RESULT_OK) {
                val returnData = data?.getStringExtra("data_return")
                Log.d("FirstActivity", "return data is $returnData")
            }
        }
    }


    /**
     * 显示右上角菜单 （三个点）
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    /**
     * 给右上角菜单（三个点）设置点击事件
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_item -> Toast.makeText(this, "You clicked Add", Toast.LENGTH_SHORT).show()
            R.id.remove_item -> Toast.makeText(this, "You clicked Remove", Toast.LENGTH_SHORT)
                .show()
        }
        return true
    }
}