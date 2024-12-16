package com.androidlearning.fragmentbestpractice

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


/**
 * 1、首先创建一个 News 新闻实体类
 *
 * 2、news_content_frag.xml  作为新闻的布局（新闻内容展示页面，上标题，下内容）
 *
 * 3、NewsContentFragment    作用：加载 news_content_frag.xml 布局
 *
 * 4、NewsContentActivity    单页布局使用，作为首页跳转过来的内容页面
 * activity_news_content.xml 布局中引用 NewsContentFragment
 *
 * 5、news_title_frag.xml    作为新闻的标题显示布局（标题列表）
 *
 * 6、news_item.xml          作为作为RecyclerView子项的布局
 *
 * 7、NewsTitleFragment      作用：加载 news_item.xml 布局
 *
 * 8、activity_main.xml 中引用 NewsTitleFragment
 *
 * 9、新建 layout-sw600dp 文件夹，在这个文件夹下再新建一个 activity_main.xml
 *
 *
 */


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
    }
}