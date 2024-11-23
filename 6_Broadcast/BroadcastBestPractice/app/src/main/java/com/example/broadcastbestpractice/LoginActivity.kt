package com.example.broadcastbestpractice

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val prefs = getPreferences(MODE_PRIVATE)

        val accountEdit: EditText = findViewById(R.id.account)
        val passwordEdit: EditText = findViewById(R.id.password)
        val loginButton: Button = findViewById(R.id.login)
        val rememberPass: CheckBox = findViewById(R.id.rememberPass)

        val isRemember = prefs.getBoolean("remember_password", false)
        if (isRemember) {
            val account = prefs.getString("account", "")
            val password = prefs.getString("password", "")
            accountEdit.setText(account)
            passwordEdit.setText(password)
            rememberPass.isChecked = true
        }

        loginButton.setOnClickListener {
            val account = accountEdit.text.toString()
            val password = passwordEdit.text.toString()

            if (account == "admin" && password == "123456") {

                val editor = prefs.edit()
                if (rememberPass.isChecked) {
                    editor.putString("account", account)
                    editor.putString("password", password)
                    editor.putBoolean("remember_password", true)
                } else {
                    editor.clear()
                }
                editor.apply()

                val loginBroIntent = Intent("walter.xj.loginuccess")
                loginBroIntent.setPackage(packageName)
                sendBroadcast(loginBroIntent)

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "无效的账号或密码", Toast.LENGTH_SHORT).show()
            }
        }
    }
}