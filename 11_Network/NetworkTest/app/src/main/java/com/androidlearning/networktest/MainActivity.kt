package com.androidlearning.networktest

import android.R.attr.name
import android.R.attr.version
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import org.xml.sax.InputSource
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.io.StringReader
import java.net.HttpURLConnection
import java.net.URL
import javax.xml.parsers.SAXParserFactory
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var responseText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        responseText = findViewById<TextView>(R.id.responseText)
        val sendRequestBtn = findViewById<Button>(R.id.sendRequestBtn)
        sendRequestBtn.setOnClickListener {
            // GET 请求
            // sendRequestWithHttpURLConnection()

            // 发送 POST 请求
            // postParamsWithHttpURLConnection()


            // HttpOK
            sendRequestWithHttpOk()
        }


        /**
         * HTTP
         */
        // val url = URL("www.bilibili.com")
        // val connection = url.openConnection() as HttpURLConnection

        // // connection.requestMethod = "GET"
        // connection.connectTimeout = 8000
        // connection.readTimeout = 8000

        // // val input = connection.inputStream


        // // // connection.disconnect()
    }

    fun httpOkOperation() {

        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://www.baidu.com")
            .build()

        val response = client.newCall(request).execute()
        val responseData = response.body?.string()
        showResponse(responseData.toString())


        /**
         * POST 请求
         */
        val postRequestBody = FormBody.Builder()
            .add("username", "admin")
            .add("password", "123456")
            .build()

        val postRequest = Request.Builder()
            .url("https://www.baidu.com")
            .post(postRequestBody)
            .build()

        val postResponse = client.newCall(postRequest).execute()
        val postResponseData = postResponse.body?.string()
        showResponse(postResponseData.toString())

    }

    /**
     * HttpOk 版本
     */
    fun sendRequestWithHttpOk() {
        thread {
            try {
                val client = OkHttpClient()

                var request = Request.Builder()
                    .url("http://192.168.31.125/get_data.xml")
                    .build()

                val response = client.newCall(request).execute()
                var responseData = response.body?.string()
                if (null != responseData) {
                    parseXMLWithSAX(responseData)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    private fun parseXMLWithSAX(xmlData: String) {
        try {

            val factory = SAXParserFactory.newInstance()
            val xmlReader = factory.newSAXParser().xmlReader
            val handler = ContentHandler()

            // 将 handle 放到 xmlReader 中
            xmlReader.contentHandler = handler
            // 开始解析
            xmlReader.parse(InputSource(StringReader(xmlData)))

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    /**
     * XML 解析
     */
    private fun parseXMLWithPull(xmlData: String) {

        try {
            val factory = XmlPullParserFactory.newInstance()

            var xmlPullParser = factory.newPullParser()

            xmlPullParser.setInput(StringReader(xmlData))

            var eventType = xmlPullParser.eventType
            var id = ""
            var name = ""
            var version = ""

            val sb = StringBuilder()
            while (eventType != XmlPullParser.END_DOCUMENT) {
                var nodeName = xmlPullParser.name
                when (eventType) {
                    XmlPullParser.START_TAG -> {

                        when (nodeName) {
                            "id" -> id = xmlPullParser.nextText()
                            "name" -> name = xmlPullParser.nextText()
                            "version" -> version = xmlPullParser.nextText()
                        }
                    }

                    XmlPullParser.END_TAG -> {
                        if ("app" == nodeName) {
                            sb.append("id       is $id\n")
                            sb.append("name     is $name\n")
                            sb.append("version  is $version\n")
                            sb.append("===================\n")
                            Log.d("MainActivity", "id is $id")
                            Log.d("MainActivity", "name is $name")
                            Log.d("MainActivity", "version is $version")
                        }
                    }
                }
                eventType = xmlPullParser.next()
            }
            showResponse(sb.toString())
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun postParamsWithHttpURLConnection() {
        thread {

            var connection: HttpURLConnection? = null

            try {
                val url = URL("https://www.bilibili.com")
                connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "POST"

                val output = DataOutputStream(connection.outputStream)
                output.writeBytes("username=admin&password=123456")

            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    fun sendRequestWithHttpURLConnection() {
        thread {

            var connection: HttpURLConnection? = null

            try {
                val response = StringBuilder()
                val url = URL("https://www.bilibili.com")
                connection = url.openConnection() as HttpURLConnection
                connection.connectTimeout = 8000
                connection.readTimeout = 8000
                val input = connection.inputStream
                val reader = BufferedReader(InputStreamReader(input))
                reader.use {
                    reader.forEachLine {
                        response.append(it)
                    }
                }
                showResponse(response.toString())

            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    private fun showResponse(response: String) {
        runOnUiThread {
            // 在这里进行 UI 操作
            responseText.text = response
        }
    }
}