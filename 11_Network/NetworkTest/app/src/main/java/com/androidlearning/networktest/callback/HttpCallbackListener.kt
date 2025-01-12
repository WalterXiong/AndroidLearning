package com.androidlearning.networktest.callback

import okhttp3.Response

interface HttpCallbackListener {

    fun onFinish(response: String)
    fun onError(e: Exception)
}