package com.androidlearning.networktest.callback

import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

object HttpUtil {

    fun sendHttpRequest(address: String, callback: Callback) {
        thread {
            try {
                val client = OkHttpClient()

                var request = Request.Builder()
                    // xml
                    // .url("http://192.168.31.125/get_data.xml")
                    // json
                    .url("http://192.168.31.125/get_data.json")
                    .build()

                client.newCall(request).enqueue(callback)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun sendHttpRequest(address: String, listener: HttpCallbackListener) {
        thread {
            try {
                val client = OkHttpClient()

                var request = Request.Builder()
                    // xml
                    // .url("http://192.168.31.125/get_data.xml")
                    // json
                    .url("http://192.168.31.125/get_data.json")
                    .build()

                val response = client.newCall(request).execute()
                var responseData = response.body?.string()
                if (null != responseData) {
                    // 回调 onFinish() 方法
                    listener.onFinish(responseData)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                // 回调onError()方法
                listener.onError(e)
            }
        }
    }
}