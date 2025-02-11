package com.androidlearning.sunnyweather

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class SunnyWeatherApplication : Application() {

    companion object {

        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

        const val TOKEN: String = "tL1q0fnbSs3NVbbD"

        const val GEO_KEY: String = "9229db405530cd37cdfba2f9b6bcfffa"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

}