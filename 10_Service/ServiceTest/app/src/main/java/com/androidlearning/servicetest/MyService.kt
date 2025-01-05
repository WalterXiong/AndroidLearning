package com.androidlearning.servicetest

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Binder
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat

class MyService : Service() {

    private val mBinder = DownloadBinder()

    class DownloadBinder : Binder() {

        fun startDownload() {
            Log.d("MyService", "startDownload() 执行了")
        }

        fun getProgress(): Int {
            Log.d("MyService", "getProgress() 执行了")
            return 0
        }
    }


    override fun onBind(intent: Intent): IBinder {
        return mBinder
    }

    /**
     * 方法会在Service创建的时候调用
     */
    override fun onCreate() {
        super.onCreate()
        Log.d("MyService", "onCreate() 方法执行了")


        /**
         * 前台服务，实现
         */
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel(
            "foregroundService", "前台 Service 通知", NotificationManager.IMPORTANCE_DEFAULT
        )
        manager.createNotificationChannel(channel)

        val intent = Intent(this, MainActivity::class.java)
        val pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val notification = NotificationCompat.Builder(this, "foregroundService")
            .setContentTitle("前台 Service 通知标题")
            .setContentText("前台 Service 通知内容")
            .setSmallIcon(R.drawable.small_icon)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.large_icon))
            .setContentIntent(pi)
            .build()

        startForeground(1, notification)
    }

    /**
     * 方法会在每次Service启动的时候调用
     * 通常情况下，如果我们希望Service一旦启动就立刻去执行某个动作，就可以将逻辑写在 onStartCommand()方法里。
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("MyService", "onStartCommand() 方法执行了")
        return super.onStartCommand(intent, flags, startId)
    }

    /**
     * 会在Service销毁的时候调用
     * 而当Service销毁时，我们又应该在onDestroy()方法中回收那些不再使用的资源。
     */
    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyService", "onDestroy() 方法执行了")
    }
}
