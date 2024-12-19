package com.androidlearning.broadcasttest

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class BootCompleteReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            Toast.makeText(context, "设备已开机", Toast.LENGTH_LONG).show()
            Log.d("BootComplete", "设备已开机")
        }

    }
}