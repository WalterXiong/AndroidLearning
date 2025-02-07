package com.androidlearning.fragmentbestpractice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NewsTitleFragment : Fragment() {

    private var isTowPane = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_title_fragment, container, false)
    }


    override fun onStart() {
        super.onStart()

        activity?.let {
            val mainActivity = it as MainActivity
            isTowPane = mainActivity.findViewById<FrameLayout>(R.id.newsContentLayout) != null

            val newsTitleRecyclerView: RecyclerView =
                mainActivity.findViewById(R.id.newsTitleRecyclerView)

            val layoutManager = LinearLayoutManager(mainActivity)

            newsTitleRecyclerView.layoutManager = layoutManager

            val adapter = NewsAdapter(getNews())

            newsTitleRecyclerView.adapter = adapter
        }

    }

    private fun getNews(): List<News> {

        val newsList = ArrayList<News>()

        for (i in 1..50) {
            val news = News("这是一个新闻标题 $i", getRandomLengthString("这是一段新闻内容 $i. "))
            newsList.add(news)
        }
        return newsList
    }

    private fun getRandomLengthString(str: String): String {
        val n = (20..200).random()
        val builder = StringBuilder()
        repeat(n) {
            builder.append(str)
        }
        return builder.toString()
    }


    inner class NewsAdapter(private val newsList: List<News>) :
        RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

        inner class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val newsTitle: TextView = view.findViewById(R.id.newsTitle_in_recyclerView)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.news_item, parent, false)
            val viewHolder = NewsViewHolder(view)

            viewHolder.itemView.setOnClickListener {

                val news = newsList[viewHolder.adapterPosition]
                if (isTowPane) {
                    // 双页面模式
                    activity?.let {
                        val mainActivity = it as MainActivity

                        val newsContentFrag = mainActivity.supportFragmentManager
                            .findFragmentById(R.id.newsContentFragment) as NewsContentFragment
                        newsContentFrag.refresh(news.title, news.content)
                    }

                } else {
                    // 单页模式直接启动 NewsContentActivity
                    NewsContentActivity.actionStart(parent.context, news.title, news.content)
                }
            }

            return viewHolder
        }

        override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
            val news = newsList[position]
            holder.newsTitle.text = news.title
        }

        override fun getItemCount() = newsList.size
    }


}