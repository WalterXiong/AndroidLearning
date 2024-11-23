package com.example.recyclerviewtest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class MainActivity : AppCompatActivity() {

    val fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        initFruitList()

        // 用于指定 recyclerview 的布局方式，这里是线性布局的意思
//        val layoutManager = LinearLayoutManager(this)
//        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        val layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)

        recyclerview.layoutManager = layoutManager

        val fruitAdapter = FruitAdapter(fruitList)

        recyclerview.adapter = fruitAdapter

    }

    private fun initFruitList() {

        repeat(2) {
            fruitList.add(Fruit(getRandomLengthString("Apple"), R.drawable.apple_pic))
            fruitList.add(Fruit(getRandomLengthString("Cherry"), R.drawable.cherry_pic))
            fruitList.add(Fruit(getRandomLengthString("Strawberry"), R.drawable.strawberry_pic))
            fruitList.add(Fruit(getRandomLengthString("Pear"), R.drawable.pear_pic))
            fruitList.add(Fruit(getRandomLengthString("Banana"), R.drawable.banana_pic))
            fruitList.add(Fruit(getRandomLengthString("Orange"), R.drawable.orange_pic))
            fruitList.add(Fruit(getRandomLengthString("Mango"), R.drawable.mango_pic))
            fruitList.add(Fruit(getRandomLengthString("Pineapple"), R.drawable.pineapple_pic))
            fruitList.add(Fruit(getRandomLengthString("Watermelon"), R.drawable.watermelon_pic))
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