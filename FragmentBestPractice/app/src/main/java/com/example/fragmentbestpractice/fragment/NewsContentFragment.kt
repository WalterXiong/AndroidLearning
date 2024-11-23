package com.example.fragmentbestpractice.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.fragmentbestpractice.R

class NewsContentFragment() : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_content, container, false)
    }

    fun refresh(title: String, content: String) {

        view?.let {

            val contentLayout = it.findViewById<LinearLayout>(R.id.contentLayout)
            val newsTitle: TextView = it.findViewById(R.id.newsTitle)
            val newsContent: TextView = it.findViewById(R.id.newsContent)

            contentLayout.visibility = View.VISIBLE

            newsTitle.text = title
            newsContent.text = content
        }
    }
}