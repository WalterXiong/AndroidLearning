package com.androidlearning.fragmentbestpractice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment

class NewsContentFragment() : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_content_fragment, container, false)
    }

    fun refresh(title: String, content: String) {

        activity?.let {
            val newsTitle: TextView = it.findViewById(R.id.newsTitle)
            val newsContent: TextView = it.findViewById(R.id.newsContent)
            val contentLayout: LinearLayout = it.findViewById(R.id.contentLayout)

            contentLayout.visibility = View.VISIBLE
            newsTitle.text = title
            newsContent.text = content
        }
    }
}