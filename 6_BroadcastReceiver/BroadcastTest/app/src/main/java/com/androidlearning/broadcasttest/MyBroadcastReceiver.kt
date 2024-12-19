package com.androidlearning.broadcasttest

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(
            context, "在我的广播接收器中收到广播",
            Toast.LENGTH_SHORT
        ).show()

    }
}