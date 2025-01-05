package com.androidlearning.servicetest

import android.content.Context
import android.util.Log

/**
 * 官方已经推荐 使用 WorkManager 替代 IntentService
 */
//class MyWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
//
//    override fun doWork(): Result {
//        // 在后台线程执行任务
//        Log.d("MyWorker", "Thread id is ${Thread.currentThread().name}")
//        // 返回成功状态
//        return Result.success()
//    }
//}