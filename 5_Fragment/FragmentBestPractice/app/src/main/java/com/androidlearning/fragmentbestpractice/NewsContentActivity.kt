package com.androidlearning.fragmentbestpractice

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class NewsContentActivity : AppCompatActivity() {

    companion object {
        fun actionStart(context: Context, title: String, content: String) {
            val intent = Intent(context, NewsContentActivity::class.java).apply {
                putExtra("news_title", title)
                putExtra("news_content", content)
            }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_news_content)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.newsCon)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val title = intent.getStringExtra("news_title")
        val content = intent.getStringExtra("news_content")

        if (title != null && content != null) {
            val newsContentFragment =
                supportFragmentManager.findFragmentById(R.id.newsContentFragment)
            val fragment = newsContentFragment as NewsContentFragment
            fragment.refresh(title, content)
        }
    }
}