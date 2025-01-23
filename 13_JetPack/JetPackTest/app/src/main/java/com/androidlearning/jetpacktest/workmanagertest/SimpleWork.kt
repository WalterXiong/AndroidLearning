package com.androidlearning.jetpacktest.workmanagertest

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters


/**
 * WorkManager的基本用法其实非常简单，主要分为以下3步：
 * (1) 定义一个后台任务，并实现具体的任务逻辑；
 * (2) 配置该后台任务的运行条件和约束信息，并构建后台任务请求；
 * (3) 将该后台任务请求传入WorkManager的enqueue()方法中，系统会在合适的时间运行。
 */
class SimpleWork(context: Context, params: WorkerParameters) : Worker(context, params) {

    override fun doWork(): Result {
        Log.d("SimpleWorker", "😘😘😘 do work in SimpleWorker")
        return Result.success()
    }
}