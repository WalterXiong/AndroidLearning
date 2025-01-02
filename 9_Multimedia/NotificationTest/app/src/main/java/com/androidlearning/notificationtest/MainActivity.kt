package com.androidlearning.notificationtest

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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


        val sendNotice: Button = findViewById(R.id.sendNotice)
        val cancelNotice: Button = findViewById(R.id.cancelNotice)

        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager


        sendNotice.setOnClickListener {

            val channel = NotificationChannel(
                "normal", "Normal", NotificationManager.IMPORTANCE_DEFAULT
            )
            manager.createNotificationChannel(channel)

            val intent = Intent(this, NotificationActivity::class.java)

            val pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

            val notification = NotificationCompat.Builder(this, "normal")
                .setContentTitle("这是通知标题")
                .setContentText("这是通知内容")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setLargeIcon(
                    BitmapFactory.decodeResource(
                        resources,
                        R.drawable.ic_launcher_foreground
                    )
                )
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build()

            manager.notify(1, notification)
        }

        cancelNotice.setOnClickListener {
            manager.cancel(1)
        }
    }
}