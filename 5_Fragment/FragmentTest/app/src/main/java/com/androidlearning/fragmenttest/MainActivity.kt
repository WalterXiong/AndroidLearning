package com.androidlearning.fragmenttest

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            replaceFragment(AnotherRightFragment())
        }

        replaceFragment(RightFragment())

    }

    private fun replaceFragment(fragment: Fragment) {
        // 获取 fragment 管理器
        val fragmentManager = supportFragmentManager
        // 开启事务
        val transaction = fragmentManager.beginTransaction()
        // 替换 fragment
        transaction.replace(R.id.rightLayout, fragment)
        // 将该 fragment 添加到返回栈中
        transaction.addToBackStack(null)
        // 提交事务
        transaction.commit()
    }
}