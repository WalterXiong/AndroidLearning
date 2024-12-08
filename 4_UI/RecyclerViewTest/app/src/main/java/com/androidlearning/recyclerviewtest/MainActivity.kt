package com.androidlearning.recyclerviewtest

import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.main_layout)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        initFruits()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        val linearLayoutManager = LinearLayoutManager(this)
        // 默认是纵向 ListView 布局, 现在是横向布局
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL

        // 瀑布流布局
        val staggeredLayoutManager =
            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)


        recyclerView.layoutManager = staggeredLayoutManager

        // 标准布局
        val standardAdapter = FruitAdapterStandard(fruitList)
        // 横向布局
        val horizonAdapter = FruitAdapterHorizon(fruitList)
        // 瀑布流布局
        val staggeredGridAdapter = FruitAdapterStaggeredGrid(fruitList)


        recyclerView.adapter = staggeredGridAdapter


    }


    val fruitList = ArrayList<Fruit>()

    private fun initFruits() {
        repeat(2) {
//            fruitList.add(Fruit("Apple", R.drawable.apple_pic))
//            fruitList.add(Fruit("Banana", R.drawable.banana_pic))
//            fruitList.add(Fruit("Orange", R.drawable.orange_pic))
//            fruitList.add(Fruit("Watermelon", R.drawable.watermelon_pic))
//            fruitList.add(Fruit("Pear", R.drawable.pear_pic))
//            fruitList.add(Fruit("Grape", R.drawable.grape_pic))
//            fruitList.add(Fruit("Pineapple", R.drawable.pineapple_pic))
//            fruitList.add(Fruit("Strawberry", R.drawable.strawberry_pic))
//            fruitList.add(Fruit("Cherry", R.drawable.cherry_pic))
//            fruitList.add(Fruit("Mango", R.drawable.mango_pic))

            fruitList.add(
                Fruit(
                    getRandomLengthString("Apple"),
                    R.drawable.apple_pic
                )
            )
            fruitList.add(
                Fruit(
                    getRandomLengthString("Banana"),
                    R.drawable.banana_pic
                )
            )
            fruitList.add(
                Fruit(
                    getRandomLengthString("Orange"),
                    R.drawable.orange_pic
                )
            )
            fruitList.add(
                Fruit(
                    getRandomLengthString("Watermelon"),
                    R.drawable.watermelon_pic
                )
            )
            fruitList.add(
                Fruit(
                    getRandomLengthString("Pear"),
                    R.drawable.pear_pic
                )
            )
            fruitList.add(
                Fruit(
                    getRandomLengthString("Grape"),
                    R.drawable.grape_pic
                )
            )
            fruitList.add(
                Fruit(
                    getRandomLengthString("Pineapple"),
                    R.drawable.pineapple_pic
                )
            )
            fruitList.add(
                Fruit(
                    getRandomLengthString("Strawberry"),
                    R.drawable.strawberry_pic
                )
            )
            fruitList.add(
                Fruit(
                    getRandomLengthString("Cherry"),
                    R.drawable.cherry_pic
                )
            )
            fruitList.add(
                Fruit(
                    getRandomLengthString("Mango"),
                    R.drawable.mango_pic
                )
            )
        }
    }

    private fun getRandomLengthString(str: String): String {
        val n = (1..20).random()
        val builder = StringBuilder()
        repeat(n) {
            builder.append(str)
        }
        return builder.toString()
    }

}