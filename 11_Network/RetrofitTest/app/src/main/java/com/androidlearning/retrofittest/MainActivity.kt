package com.androidlearning.retrofittest

import android.os.Bundle
import android.util.Log
import android.util.Log.i
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.androidlearning.retrofittest.retrofit_best_practice.ServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

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

        findViewById<Button>(R.id.getAppDataBtn).setOnClickListener {

            val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.31.125/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val appService = retrofit.create(AppService::class.java)

            appService.getAppData().enqueue(object : Callback<List<App>> {

                override fun onResponse(p0: Call<List<App>?>, p1: Response<List<App>?>) {
                    p1.body()?.let {
                        for (app in it) {
                            Log.d("MainActivity", "id is ${app.id}")
                            Log.d("MainActivity", "name is ${app.name}")
                            Log.d("MainActivity", "version is ${app.version}")
                        }
                    }
                }

                override fun onFailure(p0: Call<List<App>?>, p1: Throwable) {
                    p1.printStackTrace()
                }
            })

            val appService1 = ServiceCreator.create<AppService>()
            appService1.getAppData().enqueue(object : Callback<List<App>> {
                override fun onResponse(
                    p0: Call<List<App>?>, p1: Response<List<App>?>
                ) {
                    TODO("Not yet implemented")
                }

                override fun onFailure(
                    p0: Call<List<App>?>, p1: Throwable
                ) {
                    TODO("Not yet implemented")
                }

            })
        }
    }
}

suspend fun getAppData() {

    try {
        val appList = ServiceCreator.create<AppService>().getAppData().await()
        // 处理
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

suspend fun <T> Call<T>.await(): T {

    return suspendCoroutine { continuation ->

        enqueue(object : Callback<T> {

            override fun onResponse(p0: Call<T?>, response: Response<T?>) {
                val body = response.body()
                if (body != null) continuation.resume(body)
                else continuation.resumeWithException(
                    RuntimeException("response body is null")
                )
            }

            override fun onFailure(p0: Call<T?>, t: Throwable) {
                continuation.resumeWithException(t)
            }
        })
    }
}