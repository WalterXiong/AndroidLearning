package com.androidlearning.androidthreadtest

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.widget.Toast

class DownloadTask(context: Context) : AsyncTask<Unit, Int, Boolean>() {

    val progressDialog = ProgressDialog(context)

    override fun onPreExecute() {
        progressDialog.show() // 显示进度对话框
    }

    override fun doInBackground(vararg params: Unit?) = try {
        while (true) {
            val downloadPercent = doDownload() // 这是一个虚构的方法
            publishProgress(downloadPercent)
            if (downloadPercent >= 100) {
                break
            }
        }
        true
    } catch (e: Exception) {
        false
    }

    override fun onProgressUpdate(vararg values: Int?) {
        // 在这里更新下载进度
        progressDialog.setMessage("Downloaded ${values[0]}%")
    }

    override fun onPostExecute(result: Boolean) {
        progressDialog.dismiss()// 关闭进度对话框
        // 在这里提示下载结果
        if (result) {
            Toast.makeText(context, "Download succeeded", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, " Download failed", Toast.LENGTH_SHORT).show()
        }
    }
}
