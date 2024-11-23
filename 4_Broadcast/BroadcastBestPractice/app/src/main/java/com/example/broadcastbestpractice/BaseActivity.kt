package com.example.broadcastbestpractice

import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    private lateinit var forceOfflineReceiver: ForceOfflineReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityCollector.addActivity(this)
    }

    override fun onResume() {
        super.onResume()

        // 注册强制线下广播接收器

        val forceOfflineIntentFilter = IntentFilter()

        forceOfflineIntentFilter.addAction("com.example.broadcastbestpractice.FORCE_OFFLINE")

        forceOfflineReceiver = ForceOfflineReceiver()

        registerReceiver(forceOfflineReceiver, forceOfflineIntentFilter, RECEIVER_NOT_EXPORTED)
    }

    override fun onPause() {
        super.onPause()
        // 注销广播接收器
        unregisterReceiver(forceOfflineReceiver)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeActivity(this)
    }


    inner class ForceOfflineReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {

            AlertDialog.Builder(context).apply {
                setTitle("警告⚠")
                setMessage("您被强制下线了, 请重新登录")
                setCancelable(false)
                setPositiveButton("OK") { _, _ ->
                    ActivityCollector.finishAll()
                    val i = Intent(context, LoginActivity::class.java)
                    context?.startActivity(i)
                }
                show()
            }
        }
    }
}