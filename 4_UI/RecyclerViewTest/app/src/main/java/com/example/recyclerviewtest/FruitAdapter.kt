package com.example.recyclerviewtest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class FruitAdapter(private val fruitList: List<Fruit>) :
    RecyclerView.Adapter<FruitAdapter.FruitViewHolder>() {

    inner class FruitViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val fruitImage = view.findViewById<ImageView>(R.id.fruit_image)
        val fruitName = view.findViewById<TextView>(R.id.fruit_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitViewHolder {
//        val view =
//            LayoutInflater.from(parent.context).inflate(R.layout.fruit_item_horizon, parent, false)
//        return FruitViewHolder(view)
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.fruit_item_horizontal, parent, false)
        val viewHolder = FruitViewHolder(view)

        viewHolder.fruitImage.setOnClickListener {
            val position = viewHolder.adapterPosition
            val fruit = fruitList[position]
            Toast.makeText(
                parent.context,
                "You clicked the image of ${fruit.name}!",
                Toast.LENGTH_SHORT
            ).show()
        }

        viewHolder.fruitName.setOnClickListener {
            val position = viewHolder.adapterPosition
            val fruit = fruitList[position]
            Toast.makeText(
                parent.context,
                "You clicked the view of ${fruit.name}!",
                Toast.LENGTH_SHORT
            ).show()
        }

        return viewHolder
    }

    override fun getItemCount() = fruitList.size

    /**
     * 在每个子项被划进屏幕内要进行展时执行
     */
    override fun onBindViewHolder(holder: FruitViewHolder, position: Int) {
        val fruit = fruitList[position]
        holder.fruitImage.setImageResource(fruit.imageId)
        holder.fruitName.text = fruit.name
    }
}