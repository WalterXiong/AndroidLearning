package com.example.broadcasttest

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class BootCompleteReceiver : BroadcastReceiver() {

    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        Log.d("BootCompleteReceiver", "开机啦，我收到开机广播辣！开机啦，我收到开机广播辣！")
        Toast.makeText(context, "Boot Complete", Toast.LENGTH_LONG).show()
    }
}