package com.androidlearning.broadcastbestpractice

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val accountEdit: EditText = findViewById(R.id.accountEdit)
        val passwordEdit: EditText = findViewById(R.id.passwordEdit)


        val loginButton: Button = findViewById(R.id.login)
        loginButton.setOnClickListener {

            val account = accountEdit.text.toString()
            Log.d("LoginActivity", account)

            val password = passwordEdit.text.toString()
            Log.d("LoginActivity", password)

            // 如果账号是admin且密码是123456，就认为登录成功
            if (account == "admin" && password == "123456") {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(
                    this, "account or password is invalid", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}