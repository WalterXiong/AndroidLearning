package com.androidlearning.broadcastbestpractice

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

open class BaseActivity : AppCompatActivity() {

    private lateinit var forceOfflineBroadcastReceiver: ForceOfflineBroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityCollector.addActivity(this)
    }

    override fun onResume() {
        super.onResume()

        val intentFilter = IntentFilter()
        intentFilter.addAction("com.androidlearning.broadcastbestpractice.FORCE_OFFLINE")

        forceOfflineBroadcastReceiver = ForceOfflineBroadcastReceiver()

        ContextCompat.registerReceiver(
            this,
            forceOfflineBroadcastReceiver,
            intentFilter,
            ContextCompat.RECEIVER_EXPORTED
        )

        // registerReceiver(forceOfflineBroadcastReceiver, intentFilter, RECEIVER_EXPORTED)
    }


    override fun onPause() {
        super.onPause()
        unregisterReceiver(forceOfflineBroadcastReceiver)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeActivity(this)
    }


    inner class ForceOfflineBroadcastReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {

            context?.let {
                AlertDialog.Builder(it).apply {
                    setTitle("警告")
                    setMessage("您将被强制登出，请重新登录。")
                    setCancelable(false)
                    setPositiveButton("OK") { _, _ ->
                        ActivityCollector.fishAll()
                        val i = Intent(context, LoginActivity::class.java)
                        it.startActivity(i)
                    }
                    show()
                }
            }


        }
    }
}