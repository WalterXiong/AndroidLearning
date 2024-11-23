package com.example.fragmentbestpractice.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fragmentbestpractice.R
import com.example.fragmentbestpractice.fragment.NewsContentFragment

class NewsContentActivity : AppCompatActivity() {

    companion object {

        fun actionStart(context: Context, newsTitle: String, newsContent: String) {
            val intent = Intent(context, NewsContentActivity::class.java).apply {
                putExtra("news_title", newsTitle)
                putExtra("news_content", newsContent)

            }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        setContentView(R.layout.activity_news_content)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val newsTitle: TextView = findViewById(R.id.newsTitle)
        val newsContent: TextView = findViewById(R.id.newsContent)

        val title = intent.getStringExtra("news_title")
        val content = intent.getStringExtra("news_content")

        if (title != null && content != null) {
            val fragment = supportFragmentManager.findFragmentById(R.id.newsContentFrag)
                    as NewsContentFragment

            fragment.refresh(title, content)
        }
    }
}