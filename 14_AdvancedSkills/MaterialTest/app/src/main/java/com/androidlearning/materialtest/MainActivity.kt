package com.androidlearning.materialtest

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlin.concurrent.thread
import android.content.res.Configuration

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    private lateinit var swipeRefresh: SwipeRefreshLayout


    val fruits = mutableListOf(
        Fruit("Apple", R.drawable.apple),
        Fruit("Banana", R.drawable.banana),
        Fruit("Orange", R.drawable.orange),
        Fruit("Watermelon", R.drawable.watermelon),
        Fruit("Pear", R.drawable.pear),
        Fruit("Grape", R.drawable.grape),
        Fruit("Pineapple", R.drawable.pineapple),
        Fruit("Strawberry", R.drawable.strawberry),
        Fruit("Cherry", R.drawable.cherry),
        Fruit("Mango", R.drawable.mango)
    )

    val fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)

        setSupportActionBar(findViewById<Toolbar>(R.id.toolbar))
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_menu)
        }


        val navView = findViewById<NavigationView>(R.id.navView)
        navView.setCheckedItem(R.id.navCall)
        navView.setNavigationItemSelectedListener {
            drawerLayout.closeDrawers()
            Toast.makeText(this, "你点击了 navView", Toast.LENGTH_SHORT).show()
            true
        }

        // 悬浮式按钮
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->

            Snackbar.make(view, "数据删除", Snackbar.LENGTH_SHORT)
                .setAction("Undo") {
                    Toast.makeText(this, "你点击了 snackbar", Toast.LENGTH_SHORT).show()
                }
                .show()
        }

        // 卡片式布局
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        initFruitList()
        val layoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = layoutManager
        val fruitAdapter = FruitAdapter(this, fruitList)
        recyclerView.adapter = fruitAdapter


        // 下拉刷新布局
        swipeRefresh = findViewById<SwipeRefreshLayout>(R.id.swipeRefresh)
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary)
        swipeRefresh.setOnRefreshListener {
            refreshFruits(fruitAdapter)
        }
    }

    private fun refreshFruits(fruitAdapter: FruitAdapter) {
        thread {
            Thread.sleep(2000)
            runOnUiThread {
                initFruitList()
                fruitAdapter.notifyDataSetChanged()
                swipeRefresh.isRefreshing = false
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> drawerLayout.openDrawer(GravityCompat.START)
            R.id.backup -> Toast.makeText(this, "备份", Toast.LENGTH_SHORT).show()
            R.id.delete -> Toast.makeText(this, "删除", Toast.LENGTH_SHORT).show()
            R.id.settings -> Toast.makeText(this, "设置", Toast.LENGTH_SHORT).show()
        }
        return true
    }


    private fun initFruitList() {
        fruitList.clear()
        repeat(50) {
            val index = (0 until fruits.size).random()
            fruitList.add(fruits[index])
        }
    }

    fun isDarkTheme(context: Context): Boolean {
        val flag = context.resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK
        return flag == Configuration.UI_MODE_NIGHT_YES
    }

}