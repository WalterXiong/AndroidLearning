package com.example.fragmentbestpractice.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentbestpractice.R
import com.example.fragmentbestpractice.activity.MainActivity
import com.example.fragmentbestpractice.activity.NewsContentActivity
import com.example.fragmentbestpractice.entity.News


class NewsTitleFragment() : Fragment(), LifecycleOwner {

    private var isTowPane = false

    /**
     * 延迟初始化生命周期观察者
     */
    private lateinit var lifecycleOwner: MyFragmentLifecycleObserver

    /**
     * 自己实现一个生命周期观察者
     */
    inner class MyFragmentLifecycleObserver : DefaultLifecycleObserver {

        // 实现您需要的生命周期方法
        override fun onCreate(owner: LifecycleOwner) {

            // 当 Activity 的 onCreate() 方法完成时会调用此方法
            super.onCreate(owner)
            isTowPane = activity?.findViewById<View>(R.id.contentLayout) != null
        }
    }

    /**
     * 注册观察者
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)

        // 延迟初始化观察者
        lifecycleOwner = MyFragmentLifecycleObserver()

        // 注册观察者
        (context as? LifecycleOwner)?.lifecycle?.addObserver(lifecycleOwner)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_title, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newsRecyclerView: RecyclerView = view.findViewById(R.id.newsTitleRecyclerView)

        val linearLayoutManager = LinearLayoutManager(view.context)

        newsRecyclerView.layoutManager = linearLayoutManager

        val newsAdapter = NewsAdapter(getNews())

        newsRecyclerView.adapter = newsAdapter
    }

    /**
     * 移除观察者
     */
    override fun onDetach() {
        // 移除观察者
        (context as? LifecycleOwner)?.lifecycle?.removeObserver(lifecycleOwner)
        super.onDetach()
    }

    private fun getNews(): List<News> {
        val newsList = ArrayList<News>()

        for (i in 1..50) {
            val news =
                News("This is news title $i", getRandomLengthStr("This is news content $i"))
            newsList.add(news)
        }
        return newsList
    }

    private fun getRandomLengthStr(str: String): String {
        val n = (1..20).random()

        val sb = StringBuilder()
        repeat(n) {
            sb.append(str)
        }
        return sb.toString()
    }


    inner class NewsAdapter(private val newsList: List<News>) :
        RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

        inner class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val newsTitle: TextView = view.findViewById(R.id.item_newsTitle)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)

            val holder = NewsViewHolder(view)

            holder.newsTitle.setOnClickListener {
                val news = newsList[holder.adapterPosition]

                if (isTowPane) {
                    // 双页
                    Log.d("NewsTitleFragment -> 点击了任意一条信息", "点击事件进来了")
                    if (activity != null) {

                        val mainActivity = activity as MainActivity

                        val supportFragmentManager = mainActivity.supportFragmentManager

                        val contentFrag =
                            supportFragmentManager.findFragmentById(R.id.newsContentFrag)

                        if (contentFrag != null) {

                            val newsContentFragment = contentFrag as NewsContentFragment

                            newsContentFragment.refresh(news.title, news.content)
                        }
                    }
                } else {
                    // 单页，就启动 NewsContentActivity
                    Log.d("NewsTitleFragment -> 点击了任意一条信息", "点击事件进来了")
                    NewsContentActivity.actionStart(parent.context, news.title, news.content)
                }
            }
            return holder
        }

        override fun getItemCount() = newsList.size

        override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
            holder.newsTitle.text = newsList[position].title
        }
    }


}